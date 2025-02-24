package com.halisaha.payment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "reservation-service")
public interface ReservationClient {
    @GetMapping("/api/reservations/{id}")
    Object getReservationById(@PathVariable("id") Long id);
} 