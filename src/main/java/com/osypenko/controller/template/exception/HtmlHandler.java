package com.osypenko.controller.template.exception;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameLogs.HANDLE_ERROR;
import static com.osypenko.constant.NameSessionAttributes.*;

@Slf4j
@RequiredArgsConstructor
public class HtmlHandler {
    private final HttpSession session;

    @GetMapping(SLASH + ERROR)
    @ExceptionHandler(
            {
                    ServletRequestBindingException.class
                    , Exception.class
            }
    )
    public String getErrorPage(ServletRequestBindingException exception) {
        session.setAttribute(EXCEPTION, exception);
        log.error(HANDLE_ERROR + exception);
        return ERROR;
    }
}
