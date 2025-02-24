package com.halisaha.reservation.repository;

import com.halisaha.reservation.model.Reservation;
import com.halisaha.reservation.model.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserId(Long userId);
    List<Reservation> findByFieldId(Long fieldId);
    List<Reservation> findByStatus(ReservationStatus status);
    
    @Query("SELECT r FROM Reservation r WHERE r.fieldId = ?1 AND r.status = 'CONFIRMED' AND " +
           "((r.startTime <= ?2 AND r.endTime > ?2) OR " +
           "(r.startTime < ?3 AND r.endTime >= ?3) OR " +
           "(r.startTime >= ?2 AND r.endTime <= ?3))")
    List<Reservation> findConflictingReservations(Long fieldId, LocalDateTime startTime, LocalDateTime endTime);
} 