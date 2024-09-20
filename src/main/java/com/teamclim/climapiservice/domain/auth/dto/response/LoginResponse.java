package com.teamclim.climapiservice.domain.auth.dto.response;

import jdk.jfr.Timestamp;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class LoginResponse {
    private String accessToken;

    private String refreshToken;

    private Timestamp accessExpiredAt;

    private Timestamp refreshExpiredAt;
}
