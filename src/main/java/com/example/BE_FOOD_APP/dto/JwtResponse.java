package com.example.BE_FOOD_APP.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data

@NoArgsConstructor
public class JwtResponse {
    private Long id;
    private String email;
    private String NumberPhone;
    private String token;
    private String type = "Bearer";
    private List<String> roles;

    public JwtResponse(Long id, String email, String NumberPhone, String token, List<String> roles) {
        this.id = id;
        this.email = email;
        this.NumberPhone = NumberPhone;
        this.token = token;
        this.roles = roles;
    }
}