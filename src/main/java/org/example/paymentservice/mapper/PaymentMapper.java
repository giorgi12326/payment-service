package org.example.paymentservice.mapper;

import org.example.paymentservice.dto.PaymentDTO;
import org.example.paymentservice.entity.Payment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    PaymentDTO toDTO(Payment payment);
    List<PaymentDTO> toDTOs(List<Payment> payment);

    Payment toEntity(PaymentDTO paymentDTO);
}
