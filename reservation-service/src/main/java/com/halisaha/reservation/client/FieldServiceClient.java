package com.halisaha.reservation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "field-service")
public interface FieldServiceClient {
    
    @GetMapping("/api/fields/{id}")
    FieldResponse getField(@PathVariable("id") Long id);
}

record FieldResponse(
    Long id,
    String name,
    String location,
    Double hourlyRate
) {} 