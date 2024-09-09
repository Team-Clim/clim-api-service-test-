package com.teamclim.climapiservice.domain.auth.exception;

import com.teamclim.climapiservice.global.error.exception.ClimException;
import com.teamclim.climapiservice.global.error.exception.ErrorCode;

public class ExpiredTokenException extends ClimException {
    public static final ClimException EXCEPTION = new ExpiredTokenException();

    public ExpiredTokenException() {
        super(ErrorCode.EXPIRED_TOKEN);
    }
}
