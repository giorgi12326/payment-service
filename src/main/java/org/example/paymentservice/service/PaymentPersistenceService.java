package org.example.paymentservice.service;

import lombok.RequiredArgsConstructor;
import org.example.paymentservice.dto.OrderDTO;
import org.example.paymentservice.dto.PaymentDTO;
import org.example.paymentservice.entity.Payment;
import org.example.paymentservice.mapper.PaymentMapper;
import org.example.paymentservice.repository.PaymentRepository;
import org.example.paymentservice.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentPersistenceService {
    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository;

    @Transactional
    public PaymentDTO getPaymentDTO(PaymentDTO paymentDTO, OrderDTO orderDTOResponseEntity) {
        Payment payment = paymentMapper.toEntity(paymentDTO);
        Long id = ((CustomUserDetails) Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal())).getId();
        payment.setUserId(id);
        payment.setAmount(orderDTOResponseEntity.getAmount());
        return paymentMapper.toDTO(paymentRepository.save(payment));
    }
}
