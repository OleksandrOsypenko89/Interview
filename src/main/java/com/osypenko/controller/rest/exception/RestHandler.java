package com.osypenko.controller.rest.exception;

import lombok.extern.slf4j.Slf4j;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

@Slf4j
@RestControllerAdvice
public class RestHandler {

    @ExceptionHandler(
            value = {
                    Exception.class
                    , IllegalStateException.class
                    , IllegalArgumentException.class
                    , AccessDeniedException.class
                    , PSQLException.class
            }
    )
    protected ResponseEntity<Object> handleConflict(Exception ex) {
        String bodyOfResponse = "Handle error: " + ex.getMessage();
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(bodyOfResponse);
    }
}
