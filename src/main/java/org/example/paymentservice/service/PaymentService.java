package org.example.paymentservice.service;

import lombok.RequiredArgsConstructor;
import org.example.paymentservice.dto.PaymentDTO;
import org.example.paymentservice.entity.Payment;
import org.example.paymentservice.mapper.PaymentMapper;
import org.example.paymentservice.repository.PaymentRepository;
import org.example.paymentservice.security.CustomUserDetails;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public List<PaymentDTO> getPayments() {
        Long id = ((CustomUserDetails) Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal())).getId();
        return paymentMapper.toDTOs(paymentRepository.findAllByUserId(id));
    }

    public PaymentDTO addPayment(PaymentDTO paymentDTO) {
        Payment entity = paymentMapper.toEntity(paymentDTO);
        Long id = ((CustomUserDetails) Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal())).getId();
        entity.setUserId(id);
        return paymentMapper.toDTO(paymentRepository.save(entity));
    }
}
