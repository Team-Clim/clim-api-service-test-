package com.teamclim.climapiservice.domain.user.application.facade;

import com.teamclim.climapiservice.domain.user.domain.User;
import com.teamclim.climapiservice.domain.user.exception.NotAuthenticatedException;
import com.teamclim.climapiservice.domain.user.exception.UserNotFoundException;
import com.teamclim.climapiservice.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {
    private final UserRepository userRepository;

    public User currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()) {
            throw new NotAuthenticatedException("인증 되지 않은 유저입니다.");
        }

        String userName = authentication.getName();

        return userRepository.findByUserName(userName)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

}
