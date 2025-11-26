package org.example.paymentservice.feign;

import org.example.paymentservice.dto.FullUserDTO;
import org.example.paymentservice.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "order-service", url = "${order.service.url}")
public interface OrderClient {
    @PostMapping("/api/{id}")
    OrderDTO payForOrder(@PathVariable Long id);
}