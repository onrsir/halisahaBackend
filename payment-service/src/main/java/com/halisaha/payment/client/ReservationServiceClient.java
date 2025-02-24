package com.halisaha.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "reservation-service")
public interface ReservationServiceClient {
    
    @GetMapping("/api/reservations/{id}")
    ReservationResponse getReservation(@PathVariable("id") Long id);
    
    @PutMapping("/api/reservations/{id}/payment-status")
    void updatePaymentStatus(@PathVariable("id") Long id, PaymentStatusRequest request);
}

record ReservationResponse(
    Long id,
    Long fieldId,
    Long userId,
    String startTime,
    String endTime,
    Double totalAmount,
    String status
) {}

record PaymentStatusRequest(
    String status
) {} 