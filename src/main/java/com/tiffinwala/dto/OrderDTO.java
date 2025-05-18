package com.tiffinwala.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;
    private Long customerId;
    private Long cookId;
    private Long menuId;
    private Integer quantity;
    private LocalDateTime deliveryTime;
    private String status;
    private Double totalAmount;
}