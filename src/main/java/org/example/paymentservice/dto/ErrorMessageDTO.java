package org.example.paymentservice.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
@Builder
public class ErrorMessageDTO {
    String message;
    HttpStatus status;
    LocalDateTime timestamp;
}
