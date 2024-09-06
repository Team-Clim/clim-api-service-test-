package com.teamclim.climapiservice.global.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    //user
    USER_NOT_FOUND(404, "해당 유저가 존재 하지 않습니다"),
    USER_MISMATCH(401, "유저가 일치하지 않습니다"),
    PASSWORD_MISMATCH(401, "비밀번호가 일치 하지 않습니다"),
    INVALID_USER(401, "유효하지 않은 사용자입니다."),
    USER_EXIST(401, "유저가 이미 존재합니다");

    private final int statusCode;
    private final String ErrorMessage;
}
