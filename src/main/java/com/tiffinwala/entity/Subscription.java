package com.tiffinwala.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "subscriptions")
@Data
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "cook_id")
    private User cook;

    private LocalDate startDate;
    private LocalDate endDate;
    private String frequency; // DAILY, WEEKLY
    private String status; // ACTIVE, PAUSED, CANCELLED
}