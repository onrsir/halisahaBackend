package com.halisaha.reservation.service;

import com.halisaha.reservation.client.FieldClient;
import com.halisaha.reservation.client.UserClient;
import com.halisaha.reservation.model.Reservation;
import com.halisaha.reservation.model.ReservationStatus;
import com.halisaha.reservation.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private FieldClient fieldClient;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with id: " + id));
    }

    public List<Reservation> getReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    public List<Reservation> getReservationsByFieldId(Long fieldId) {
        return reservationRepository.findByFieldId(fieldId);
    }

    @Transactional
    public Reservation createReservation(Reservation reservation) {
        // Kullanıcı ve saha kontrolü
        userClient.getUserById(reservation.getUserId());
        fieldClient.getFieldById(reservation.getFieldId());

        // Çakışma kontrolü
        List<Reservation> conflictingReservations = reservationRepository.findConflictingReservations(
                reservation.getFieldId(),
                reservation.getStartTime(),
                reservation.getEndTime()
        );

        if (!conflictingReservations.isEmpty()) {
            throw new IllegalStateException("The field is already reserved for the specified time period");
        }

        return reservationRepository.save(reservation);
    }

    @Transactional
    public Reservation updateReservation(Long id, Reservation reservation) {
        Reservation existingReservation = getReservationById(id);
        
        // Sadece PENDING durumundaki rezervasyonlar güncellenebilir
        if (existingReservation.getStatus() != ReservationStatus.PENDING) {
            throw new IllegalStateException("Only pending reservations can be updated");
        }

        reservation.setId(id);
        return reservationRepository.save(reservation);
    }

    @Transactional
    public Reservation confirmReservation(Long id) {
        Reservation reservation = getReservationById(id);
        reservation.setStatus(ReservationStatus.CONFIRMED);
        return reservationRepository.save(reservation);
    }

    @Transactional
    public Reservation cancelReservation(Long id) {
        Reservation reservation = getReservationById(id);
        
        // Sadece PENDING veya CONFIRMED durumundaki rezervasyonlar iptal edilebilir
        if (reservation.getStatus() == ReservationStatus.CANCELLED || 
            reservation.getStatus() == ReservationStatus.COMPLETED) {
            throw new IllegalStateException("Reservation cannot be cancelled in its current state");
        }

        reservation.setStatus(ReservationStatus.CANCELLED);
        return reservationRepository.save(reservation);
    }

    @Transactional
    public Reservation completeReservation(Long id) {
        Reservation reservation = getReservationById(id);
        
        if (reservation.getStatus() != ReservationStatus.CONFIRMED) {
            throw new IllegalStateException("Only confirmed reservations can be completed");
        }

        if (reservation.getEndTime().isAfter(LocalDateTime.now())) {
            throw new IllegalStateException("Reservation end time has not been reached yet");
        }

        reservation.setStatus(ReservationStatus.COMPLETED);
        return reservationRepository.save(reservation);
    }
} 