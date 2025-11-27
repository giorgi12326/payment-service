package org.example.paymentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.paymentservice.entity.PaymentMethod;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long id;

    private PaymentMethod paymentMethod;

    private Float amount;

    private Long userId;

    private Long orderId;

    private LocalDateTime createdAt;
}
