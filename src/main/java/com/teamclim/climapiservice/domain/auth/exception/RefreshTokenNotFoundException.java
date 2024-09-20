package com.teamclim.climapiservice.domain.auth.exception;

import com.teamclim.climapiservice.global.error.exception.ClimException;
import com.teamclim.climapiservice.global.error.exception.ErrorCode;

public class RefreshTokenNotFoundException extends ClimException {
    public static final ClimException EXCEPTION = new RefreshTokenNotFoundException();

    public RefreshTokenNotFoundException() {
        super(ErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
