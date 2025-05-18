package com.tiffinwala.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String phone;
    private String password;
    private String role;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
}