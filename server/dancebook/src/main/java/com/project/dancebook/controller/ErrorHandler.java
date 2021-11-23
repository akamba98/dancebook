package com.project.dancebook.controller;

import com.project.dancebook.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.support.RequestContext;

@RestControllerAdvice
@CrossOrigin
public class ErrorHandler {

    private RequestContext requestContext;
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO handleExeption(IllegalArgumentException e){
        return new ResponseDTO(400,e.getMessage());
    }
}
