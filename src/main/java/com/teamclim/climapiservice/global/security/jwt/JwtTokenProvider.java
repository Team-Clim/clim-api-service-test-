package com.teamclim.climapiservice.global.security.jwt;

import com.teamclim.climapiservice.domain.auth.domain.RefreshToken;
import com.teamclim.climapiservice.domain.auth.dto.response.LoginResponse;
import com.teamclim.climapiservice.domain.auth.exception.ExpiredTokenException;
import com.teamclim.climapiservice.domain.auth.exception.InvalidTokenException;
import com.teamclim.climapiservice.domain.auth.repository.RefreshTokenRepository;
import com.teamclim.climapiservice.domain.user.domain.User;
import com.teamclim.climapiservice.domain.user.exception.UserNotFoundException;
import com.teamclim.climapiservice.domain.user.repository.UserRepository;
import com.teamclim.climapiservice.global.security.auth.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserRepository userRepository;



    public String createAccessToken(String user_name) {
        Date now = new Date(); //코드를 실행한 시점의 현재 날짜와 시간이 저장(일시적)

        return Jwts.builder()
                .setSubject(user_name) //토큰의 소유자
                .claim("type", "access") //액세스 토큰임을 나타냄
                .setIssuedAt(now) //토큰 발행 시간 정보
                .setExpiration(new java.sql.Timestamp(now.getTime() + jwtProperties.getAccessExpiration() * 1000)) //토큰의 만료 시간 설정
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret()) //HS512 알고리즘, 비밀 키를 Jwtproperties에서 가져옴
                .compact();


    }

    public String createRefreshToken(String user_name) {
        Date now = new Date();

        String refreshToken = Jwts.builder()
                .claim("type", "refresh")  //refresh 토큰임을 나타냄
                .setIssuedAt(now)
                .setExpiration(new java.sql.Timestamp(now.getTime() + jwtProperties.getRefreshExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret()) //
                .compact();


        refreshTokenRepository.save(
                RefreshToken.builder()
                        .user_name(user_name)
                        .token(refreshToken)
                        .timeToLive((jwtProperties.getRefreshExpiration()))
                        .build()
        );

        return refreshToken;
    }

    // 토큰에 담겨 있는 userId로 SpringSecurity Authentication 정보를 반환 하는 메서드
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }


    public Claims getClaims(String token) {
        try {
            return Jwts
                    .parser() //JWT parser 생성
                    .setSigningKey(jwtProperties.getSecret())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }
        catch (ExpiredTokenException E) {
            throw ExpiredTokenException.EXCEPTION;
        }
        catch (Exception E) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    public LoginResponse receiveToken(String user_name) {
        Date now = new Date();

        User user = userRepository.findByUserName(user_name)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        return LoginResponse.builder()
                .accessToken(createAccessToken(user_name))
                .refreshToken(createRefreshToken(user_name))
                .accessExpiredAt((jdk.jfr.Timestamp) new Date(now.getTime() + jwtProperties.getAccessExpiration()))
                .refreshExpiredAt((jdk.jfr.Timestamp) new Date(now.getTime() + jwtProperties.getRefreshExpiration()))
                .build();
    }

    // HTTP 요청 헤더에서 토큰을 가져오는 메서드
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(jwtProperties.getHeader());

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(jwtProperties.getPrefix())
                && bearerToken.length() > jwtProperties.getPrefix().length() + 1) {
            return bearerToken.substring(7);
        }

        return null;
    }
}
