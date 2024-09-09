package com.teamclim.climapiservice.domain.auth.exception;

import com.teamclim.climapiservice.global.error.exception.ClimException;
import com.teamclim.climapiservice.global.error.exception.ErrorCode;

public class InvalidTokenException extends ClimException {
    public static final ClimException EXCEPTION = new InvalidTokenException();

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
