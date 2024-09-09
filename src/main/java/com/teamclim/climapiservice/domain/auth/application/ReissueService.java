package com.teamclim.climapiservice.domain.auth.application;

import com.teamclim.climapiservice.domain.auth.domain.RefreshToken;
import com.teamclim.climapiservice.domain.auth.dto.request.RefreshTokenRequest;
import com.teamclim.climapiservice.domain.auth.dto.response.LoginResponse;
import com.teamclim.climapiservice.domain.auth.exception.RefreshTokenNotFoundException;
import com.teamclim.climapiservice.domain.auth.repository.RefreshTokenRepository;
import com.teamclim.climapiservice.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReissueService {
    private final JwtTokenProvider jwtTokenProvider;

    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public LoginResponse reissue(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(request.getToken())
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);
        return jwtTokenProvider.receiveToken(refreshToken.getUser_name());
    }
}
