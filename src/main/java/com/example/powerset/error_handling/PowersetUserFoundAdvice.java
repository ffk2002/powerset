package com.example.powerset.error_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PowersetUserFoundAdvice {
    @ResponseBody
    @ExceptionHandler(PowersetUserFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String PowersetUserFoundHandler(PowersetUserFoundException ex) {
        return ex.getMessage();
    }
}
