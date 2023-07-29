package com.example.powerset;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SetNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String setNotFoundHandler(SetNotFoundException ex) {
        return ex.getMessage();
    }
}
