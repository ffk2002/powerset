package com.example.powerset;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidSetInputAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidSetInputException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidSetInputHandler(InvalidSetInputException ex) {
        return ex.getMessage();
    }
}
