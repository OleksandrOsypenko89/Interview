package com.osypenko.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class AdviceExceptionController {
    private final ExceptionService service;

    @ExceptionHandler
    public void handler(ServletRequestBindingException exception) {
        log.error(exception.getMessage(), exception);
        service.methodThrowsException();
    }
}
