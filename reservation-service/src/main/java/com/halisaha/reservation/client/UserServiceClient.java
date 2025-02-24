package com.halisaha.reservation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceClient {
    
    @GetMapping("/api/users/{id}")
    UserResponse getUser(@PathVariable("id") Long id);
}

record UserResponse(
    Long id,
    String name,
    String email,
    String phone
) {} 