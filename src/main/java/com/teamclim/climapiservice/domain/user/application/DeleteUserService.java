package com.teamclim.climapiservice.domain.user.application;

import com.teamclim.climapiservice.domain.user.application.facade.UserFacade;
import com.teamclim.climapiservice.domain.user.domain.User;
import com.teamclim.climapiservice.domain.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@RequiredArgsConstructor
public class DeleteUserService {
    private final UserFacade userFacade;
    private final UserRepository userRepository;

    @Transactional
    public void deleteUser(Long id) {
        User user = userFacade.currentUser();

        userRepository.deleteById(id);
    }
}
