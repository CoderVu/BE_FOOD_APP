package com.example.BE_FOOD_APP.equest;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;



@Data
public class LoginRequest {

    private String numberPhone;

    private String password;
}