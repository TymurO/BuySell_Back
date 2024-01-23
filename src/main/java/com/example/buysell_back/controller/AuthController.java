package com.example.buysell_back.controller;

import com.example.buysell_back.dto.JwtRequest;
import com.example.buysell_back.dto.RegistrationUserDto;
import com.example.buysell_back.model.Response;
import com.example.buysell_back.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;


    @PostMapping("/auth")
    public ResponseEntity<Response> createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/reg")
    public ResponseEntity<Response> createUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return authService.createUser(registrationUserDto);
    }

    @PostMapping("/log/out")
    public ResponseEntity<Response> logout() {
        return authService.logout();
    }
}
