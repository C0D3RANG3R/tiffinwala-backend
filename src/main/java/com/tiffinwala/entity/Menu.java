package com.tiffinwala.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "menus")
@Data
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cook_id")
    private User cook;

    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private String cuisineType;
}