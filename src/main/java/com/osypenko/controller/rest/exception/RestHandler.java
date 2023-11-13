package com.osypenko.controller.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@RestControllerAdvice
public class RestHandler {

    @ExceptionHandler(
            value = {
                    Exception.class
                    , IllegalStateException.class
                    , IllegalArgumentException.class
                    , AccessDeniedException.class
            }
    )
    protected ResponseEntity<Object> handleConflict(Exception ex) {
        String bodyOfResponse = "Handle error: " + ex.getMessage();
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(bodyOfResponse);
    }
}
