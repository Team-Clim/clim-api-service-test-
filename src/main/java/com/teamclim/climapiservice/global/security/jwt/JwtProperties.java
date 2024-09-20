package com.teamclim.climapiservice.global.security.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

@Getter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String header; //JWT 토큰이 포함될 HTTP 헤더 이름
    private String prefix; //JWT 토큰 앞에 붙는 접두사
    private String secret; //JWT 토큰을 서명하고 검증하는 데 사용되는 비밀 키
    private Long accessExpiration; //Access Token의 만료 시간
    private Long refreshExpiration; //Refresh Token의 만료 시간
}
