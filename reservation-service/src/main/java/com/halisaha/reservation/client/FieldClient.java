package com.halisaha.reservation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "field-service")
public interface FieldClient {
    @GetMapping("/api/fields/{id}")
    Object getFieldById(@PathVariable("id") Long id);
} 