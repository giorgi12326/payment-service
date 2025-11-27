package org.example.paymentservice.service;

import feign.RetryableException;
import lombok.RequiredArgsConstructor;
import org.example.paymentservice.dto.OrderDTO;
import org.example.paymentservice.dto.PaymentDTO;
import org.example.paymentservice.exception.ConflictException;
import org.example.paymentservice.feign.OrderClient;
import org.example.paymentservice.mapper.PaymentMapper;
import org.example.paymentservice.repository.PaymentRepository;
import org.example.paymentservice.security.CustomUserDetails;
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
    private final PaymentPersistenceService paymentPersistenceService;

    public List<PaymentDTO> getPayments() {
        Long id = ((CustomUserDetails) Objects.requireNonNull(Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal())).getId();
        return paymentMapper.toDTOs(paymentRepository.findAllByUserId(id));
    }

    public PaymentDTO addPayment(PaymentDTO paymentDTO) {
        OrderDTO orderDTOResponseEntity;
        try {
            orderDTOResponseEntity = orderClient.payForOrder(paymentDTO.getOrderId());
        }
        catch (RetryableException e) {
            OrderDTO unpay = orderClient.unpayForOrder(paymentDTO.getOrderId());
            throw new ConflictException("order status response lost, did compensation! status now:" + unpay.getStatus());
        }
        return paymentPersistenceService.getPaymentDTO(paymentDTO, orderDTOResponseEntity);
    }
}
