package com.fmi.aop.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountAlreadyEnabledException extends RuntimeException {

    public AccountAlreadyEnabledException(String exception){
        super(exception);
    }

}
