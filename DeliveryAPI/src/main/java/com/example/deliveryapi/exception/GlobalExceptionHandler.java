package com.example.deliveryapi.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({ CustomException.class })
    protected ResponseEntity<?> handleCustomException(CustomException ex) {
        String errorMessage = ex.getErrorCode().getMessage();
        log.error(errorMessage);
        return ResponseEntity.status(ex.getErrorCode().getStatus()).body(errorMessage);
    }

    @ExceptionHandler({ Exception.class })
    protected ResponseEntity<?> handleServerException() {
        log.error(INTERNAL_SERVER_ERROR.getReasonPhrase());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(INTERNAL_SERVER_ERROR.getReasonPhrase());
    }
}
