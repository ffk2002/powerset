package com.example.powerset.error_handling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PowersetUserExistsAdvice {
    @ResponseBody
    @ExceptionHandler(PowersetUserExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String PowersetUserExistsHandler(PowersetUserExistsException ex) {
        return ex.getMessage();
    }
}
