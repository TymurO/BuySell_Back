package com.example.buysell_back.dto;

import lombok.Data;

@Data
public class RegistrationUserDto {

    private String username;

    private String password;

    private String confirmPassword;
}
