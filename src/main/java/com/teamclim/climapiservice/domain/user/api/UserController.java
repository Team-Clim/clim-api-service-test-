package com.teamclim.climapiservice.domain.user.api;

import com.teamclim.climapiservice.domain.user.application.DeleteUserService;
import com.teamclim.climapiservice.domain.user.application.MyInfoService;
import com.teamclim.climapiservice.domain.user.application.UpdateMyInfoService;
import com.teamclim.climapiservice.domain.user.dto.request.MyInfoRequest;
import com.teamclim.climapiservice.domain.user.dto.response.MyInfoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final DeleteUserService deleteUserService;
    private final MyInfoService myInfoService;
    private final UpdateMyInfoService updateMyInfoService;

    @GetMapping("/my-info")
    public MyInfoResponse MyInfoUser() {
        return myInfoService.Info();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        deleteUserService.deleteUSer(id);
    }

    @PatchMapping("/update")
    public void updateUser(@RequestBody @Valid MyInfoRequest request) {
        updateMyInfoService.updateMyInfo(request);
    }

}
