package com.teamclim.climapiservice.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class RegisterRequest {

    @NotBlank
    @Size(min = 2, max = 20)
    private String user_name;

    @NotBlank
    @Size(min = 8, max = 30)
    private String password;

    @NotBlank
    @Email
    private String email;
}
