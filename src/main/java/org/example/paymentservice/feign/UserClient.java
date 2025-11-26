package org.example.paymentservice.feign;

import org.example.paymentservice.dto.FullUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "${user.service.url}")
public interface UserClient {
    @GetMapping("/api/auth")
    FullUserDTO getUserByUsername(@RequestParam("username") String username);

    @GetMapping("/api/auth/{id}/exists")
    boolean userExists(@PathVariable Long id);
}