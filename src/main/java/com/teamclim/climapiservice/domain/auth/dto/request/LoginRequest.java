package com.teamclim.climapiservice.domain.auth.dto.request;

import lombok.Getter;

@Getter
public class LoginRequest {
    private String user_name;

    private String password;
}
