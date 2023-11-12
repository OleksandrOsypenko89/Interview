package com.osypenko.controller.html;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.PASSWORD_FLAG;
import static com.osypenko.constant.NameSessionAttributes.REGISTRATION_FLAG;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final HttpSession session;

    @GetMapping(LOGIN)
    public String login() {
        session.removeAttribute(REGISTRATION_FLAG);
        session.removeAttribute(PASSWORD_FLAG);
        return DIRECTORY_USER_PAGES + LOGIN;
    }
}