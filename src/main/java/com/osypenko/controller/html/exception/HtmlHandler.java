package com.osypenko.controller.html.exception;

import com.osypenko.exception.UserException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.*;

@Slf4j
@ControllerAdvice(basePackages = "com/osypenko/controller/html")
@RequiredArgsConstructor
public class HtmlHandler {
    private final HttpSession session;

    @GetMapping(SLASH + ERROR)
    @ExceptionHandler(
            {
                    ServletRequestBindingException.class
                    , UserException.class
                    , Exception.class
            }
    )
    public String getErrorPage(ServletRequestBindingException exception) {
        session.setAttribute(EXCEPTION, exception);
        return ERROR;
    }
}
