package com.example.buysell_back.service;

import com.example.buysell_back.dto.JwtRequest;
import com.example.buysell_back.dto.RegistrationUserDto;
import com.example.buysell_back.model.Response;
import com.example.buysell_back.model.User;
import com.example.buysell_back.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserDetailsService userDetailsService;

    private final JwtTokenUtils jwtTokenUtils;

    private final AuthenticationManager authenticationManager;

    public ResponseEntity<Response> createAuthToken(JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .message("Login failed")
                            .reason("Incorrect login or password")
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build()
            );
        }
        User user = userDetailsService.getUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtils.generateToken(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Set-Cookie", "jwt=" + token + "; Max-Age=86400");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Login succeeded")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    public ResponseEntity<Response> createUser(RegistrationUserDto registrationUserDto) {
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .data(Map.of("user", registrationUserDto))
                            .message("User wasn't created")
                            .reason("Password do not match")
                            .status(HttpStatus.BAD_GATEWAY)
                            .statusCode(HttpStatus.BAD_GATEWAY.value())
                            .build()
            );
        }
        else if (userDetailsService.getUserByUsername(registrationUserDto.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    Response.builder()
                            .timeStamp(LocalDateTime.now())
                            .data(Map.of("user", registrationUserDto))
                            .message("User wasn't created")
                            .reason("Username is in use")
                            .status(HttpStatus.BAD_GATEWAY)
                            .statusCode(HttpStatus.BAD_GATEWAY.value())
                            .build()
            );
        }
        userDetailsService.registration(registrationUserDto);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .data(Map.of("user", registrationUserDto))
                        .message("User was registered")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    public ResponseEntity<Response> logout() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Set-Cookie", "jwt=none; Max-Age=0");
        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(
                Response.builder()
                        .timeStamp(LocalDateTime.now())
                        .message("Logout succeeded")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
