package com.teamclim.climapiservice.global.error.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClimException extends RuntimeException {
    private final ErrorCode errorCode;
}
