package com.teamclim.climapiservice.domain.user.exception;

public class NotAuthenticatedException extends RuntimeException{
    public NotAuthenticatedException(String message){
        super(message);
    }
}
