package com.teamclim.climapiservice.domain.user.application;

import com.teamclim.climapiservice.domain.user.application.facade.UserFacade;
import com.teamclim.climapiservice.domain.user.domain.User;
import com.teamclim.climapiservice.domain.user.dto.response.MyInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MyInfoService {
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public MyInfoResponse info() {
        User user = userFacade.currentUser();

        return MyInfoResponse.builder()
                .user_name(user.getUser_name())
                .email(user.getEmail())
                .build();

    }
}
