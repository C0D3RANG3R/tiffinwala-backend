package com.tiffinwala.dto;

import lombok.Data;

@Data
public class RatingDTO {
    private Long id;
    private Long orderId;
    private Long customerId;
    private Long cookId;
    private Integer rating;
    private String comment;
}