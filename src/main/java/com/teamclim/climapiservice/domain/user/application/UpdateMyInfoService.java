package com.teamclim.climapiservice.domain.user.application;

import com.teamclim.climapiservice.domain.user.application.facade.UserFacade;
import com.teamclim.climapiservice.domain.user.domain.User;
import com.teamclim.climapiservice.domain.user.dto.request.MyInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateMyInfoService {
    private final UserFacade userFacade;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void updateMyInfo(MyInfoRequest myInfoRequest) {
        User user = userFacade.currentUser();

        user.updateMyInfo(
                myInfoRequest.getUser_name(),
                passwordEncoder.encode(myInfoRequest.getPassword()),
                myInfoRequest.getEmail(),
                myInfoRequest.getUpdated_at()
        );

    }
}
