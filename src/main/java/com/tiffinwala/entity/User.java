package com.tiffinwala.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    private String password;
    private String role; // CUSTOMER, COOK, ADMIN
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
}