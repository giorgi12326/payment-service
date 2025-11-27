package org.example.paymentservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.paymentservice.dto.PaymentDTO;
import org.example.paymentservice.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getPayments() {
        return ResponseEntity.ok(paymentService.getPayments());
    }
    @PostMapping
    public ResponseEntity<PaymentDTO> addPayment(@RequestBody @Validated PaymentDTO paymentDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentService.addPayment(paymentDTO));
    }
}
