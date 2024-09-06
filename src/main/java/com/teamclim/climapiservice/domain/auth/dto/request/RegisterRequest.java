package com.teamclim.climapiservice.domain.auth.dto.request;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class RegisterRequest {
    private Long id;

    private String user_name;

    private String password;

    private String email;

    private Timestamp created_at;
}
