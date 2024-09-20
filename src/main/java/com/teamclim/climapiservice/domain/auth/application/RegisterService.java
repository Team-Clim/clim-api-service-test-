package com.teamclim.climapiservice.domain.auth.application;

import com.teamclim.climapiservice.domain.auth.dto.request.RegisterRequest;
import com.teamclim.climapiservice.domain.auth.exception.UserExistException;
import com.teamclim.climapiservice.domain.user.domain.User;
import com.teamclim.climapiservice.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;

@Service
@RequiredArgsConstructor
public class RegisterService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public void register(RegisterRequest request) {
        if(userRepository.existsByUserName(request.getUser_name())) {
            throw UserExistException.EXCEPTION;
        }

        userRepository.save(
                User.builder()
                        .user_name(request.getUser_name())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .build()
        );
    }
}
