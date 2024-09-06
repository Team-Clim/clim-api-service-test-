package com.teamclim.climapiservice.domain.auth.exception;

import com.teamclim.climapiservice.global.exception.ClimException;
import com.teamclim.climapiservice.global.exception.ErrorCode;

public class UserExistException extends ClimException {
    public static final ClimException EXCEPTION = new UserExistException();

    private UserExistException(){
        super(ErrorCode.USER_EXIST);
    }
}
