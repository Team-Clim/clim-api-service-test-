package com.teamclim.climapiservice.domain.auth.application;

import com.teamclim.climapiservice.domain.auth.dto.request.LoginRequest;
import com.teamclim.climapiservice.domain.auth.dto.response.LoginResponse;
import com.teamclim.climapiservice.domain.auth.exception.PasswordMismatchException;
import com.teamclim.climapiservice.domain.user.domain.User;
import com.teamclim.climapiservice.domain.user.exception.UserNotFoundException;
import com.teamclim.climapiservice.domain.user.repository.UserRepository;
import com.teamclim.climapiservice.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUserName(request.getUser_name())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw PasswordMismatchException.EXCEPTION;

        return jwtTokenProvider.receiveToken(request.getUser_name());
    }

}
