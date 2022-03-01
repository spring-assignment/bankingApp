package com.cts.springboot.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountNotFoundExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorMessage> toResponse(AccountNotFoundException ex){
        ErrorMessage error = new ErrorMessage(ex.getMessage(),404);
        return new ResponseEntity<ErrorMessage>(error, HttpStatus.NOT_FOUND);
    }
}
