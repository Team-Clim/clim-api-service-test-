package com.teamclim.climapiservice.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MyInfoResponse {
    private String user_name;

    private String email;

}
