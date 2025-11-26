package org.example.paymentservice.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private Long productId;
    private String productName;
    private String productDescription;
    private Integer quantity;
    private Float price;
}