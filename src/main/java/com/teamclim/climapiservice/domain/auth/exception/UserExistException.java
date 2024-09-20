package com.teamclim.climapiservice.domain.auth.exception;

import com.teamclim.climapiservice.global.error.exception.ClimException;
import com.teamclim.climapiservice.global.error.exception.ErrorCode;

public class UserExistException extends ClimException {
    public static final ClimException EXCEPTION = new UserExistException();

    private UserExistException(){
        super(ErrorCode.USER_EXIST);
    }
}
