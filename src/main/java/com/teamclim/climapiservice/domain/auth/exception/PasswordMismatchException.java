package com.teamclim.climapiservice.domain.auth.exception;

import com.teamclim.climapiservice.global.error.exception.ClimException;
import com.teamclim.climapiservice.global.error.exception.ErrorCode;

public class PasswordMismatchException extends ClimException {
    public static final ClimException EXCEPTION = new PasswordMismatchException();

    public PasswordMismatchException() {
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
