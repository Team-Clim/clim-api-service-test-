package com.teamclim.climapiservice.domain.auth.api;

import com.teamclim.climapiservice.domain.auth.application.LoginService;
import com.teamclim.climapiservice.domain.auth.application.RegisterService;
import com.teamclim.climapiservice.domain.auth.application.ReissueService;
import com.teamclim.climapiservice.domain.auth.dto.request.LoginRequest;
import com.teamclim.climapiservice.domain.auth.dto.request.RefreshTokenRequest;
import com.teamclim.climapiservice.domain.auth.dto.request.RegisterRequest;
import com.teamclim.climapiservice.domain.auth.dto.response.LoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final RegisterService registerService;
    private final LoginService loginService;
    private final ReissueService reissueService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequest request) {
        registerService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.login(request);
    }

    @PatchMapping("/reissue")
    public LoginResponse reissue(RefreshTokenRequest request) {
        return reissueService.reissue(request);
    }
}
