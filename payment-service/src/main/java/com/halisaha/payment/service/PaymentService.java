package com.halisaha.payment.service;

import com.halisaha.payment.client.ReservationClient;
import com.halisaha.payment.client.UserClient;
import com.halisaha.payment.model.Payment;
import com.halisaha.payment.model.PaymentStatus;
import com.halisaha.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ReservationClient reservationClient;
    private final UserClient userClient;

    @Transactional
    public Payment createPayment(Payment payment) {
        // Validate reservation and user
        Object reservation = reservationClient.getReservationById(payment.getReservationId());
        Object user = userClient.getUserById(payment.getUserId());
        if (reservation == null || user == null) {
            throw new RuntimeException("Invalid reservation or user");
        }

        // Simüle edilmiş ödeme işlemi
        String mockPaymentId = UUID.randomUUID().toString();
        payment.setStripePaymentIntentId(mockPaymentId);
        payment.setStatus(PaymentStatus.PENDING);
        return paymentRepository.save(payment);
    }

    public Payment confirmPayment(String paymentIntentId) {
        Payment payment = paymentRepository.findByStripePaymentIntentId(paymentIntentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        // Simüle edilmiş ödeme onayı - %90 başarı oranı
        if (Math.random() < 0.9) {
            payment.setStatus(PaymentStatus.COMPLETED);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
            payment.setErrorMessage("Payment simulation failed");
        }
        
        return paymentRepository.save(payment);
    }

    public Payment refundPayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        if (payment.getStatus() != PaymentStatus.COMPLETED) {
            throw new RuntimeException("Payment cannot be refunded");
        }

        // Simüle edilmiş iade işlemi - %95 başarı oranı
        if (Math.random() < 0.95) {
            payment.setStatus(PaymentStatus.REFUNDED);
        } else {
            payment.setErrorMessage("Refund simulation failed");
        }
        
        return paymentRepository.save(payment);
    }

    public List<Payment> getPaymentsByUserId(Long userId) {
        return paymentRepository.findByUserId(userId);
    }

    public List<Payment> getPaymentsByReservationId(Long reservationId) {
        return paymentRepository.findByReservationId(reservationId);
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    public List<Payment> getPaymentsByStatus(PaymentStatus status) {
        return paymentRepository.findByStatus(status);
    }
} 