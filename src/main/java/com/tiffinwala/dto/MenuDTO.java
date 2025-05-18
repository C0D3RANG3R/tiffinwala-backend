package com.tiffinwala.dto;

import lombok.Data;

@Data
public class MenuDTO {
    private Long id;
    private Long cookId;
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private String cuisineType;
}