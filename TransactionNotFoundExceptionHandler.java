package com.cts.springboot.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransactionNotFoundExceptionHandler {

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorMessage> toResponse(TransactionNotFoundException ex) {
        ErrorMessage error = new ErrorMessage(ex.getMessage(), 400);
        return new ResponseEntity<ErrorMessage>(error, HttpStatus.BAD_REQUEST);
    }
}
