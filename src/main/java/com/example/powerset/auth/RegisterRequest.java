package com.example.powerset.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RegisterRequest {
    private String name;
    private String password;
    private String username;
    private String email;
    private LocalDate dob;
    private Integer weight;
}
