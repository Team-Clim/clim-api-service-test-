package com.teamclim.climapiservice.domain.user.dto.request;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class MyInfoRequest {
    private String user_name;

    private String password;

    private String email;

    private Timestamp updated_at;

}
