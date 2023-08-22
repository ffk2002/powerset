package com.example.powerset.error_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class PowersetUserNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(PowersetUserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String PowersetUserNotFoundHandler(PowersetUserNotFoundException ex) {
        return ex.getMessage();
    }
}
