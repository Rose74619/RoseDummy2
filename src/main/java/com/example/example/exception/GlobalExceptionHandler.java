package com.example.example.exception;

import com.example.example.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorDetails> HandlerResourceNotFoundException(ResourceNotFound ex){
        ErrorDetails err=new ErrorDetails(ex.getMessage(), new Date());
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> HandlerResourceNotFoundException(Exception ex){
        ErrorDetails err=new ErrorDetails(ex.getMessage(), new Date());
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
