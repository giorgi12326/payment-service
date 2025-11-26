package org.example.paymentservice.service;

import lombok.RequiredArgsConstructor;
import org.example.paymentservice.dto.OrderDTO;
import org.example.paymentservice.dto.PaymentDTO;
import org.example.paymentservice.entity.Payment;
import org.example.paymentservice.exception.ConflictException;
import org.example.paymentservice.feign.OrderClient;
import org.example.paymentservice.mapper.PaymentMapper;
import org.example.paymentservice.repository.PaymentRepository;
import org.example.paymentservice.security.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderClient orderClient;

    public List<PaymentDTO> getPayments() {
        Long id = ((CustomUserDetails) Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal())).getId();
        return paymentMapper.toDTOs(paymentRepository.findAllByUserId(id));
    }

    @Transactional
    public PaymentDTO addPayment(PaymentDTO paymentDTO) {
        OrderDTO orderDTOResponseEntity = orderClient.payForOrder(paymentDTO.getOrderId());
        if(orderDTOResponseEntity.getStatus().equals("PAID")) {
            throw new ConflictException("Payment is already paid");
        }

        Payment payment = paymentMapper.toEntity(paymentDTO);
        Long id = ((CustomUserDetails) Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal())).getId();
        payment.setUserId(id);
        payment.setAmount(orderDTOResponseEntity.getAmount());
        return paymentMapper.toDTO(paymentRepository.save(payment));
    }
}
