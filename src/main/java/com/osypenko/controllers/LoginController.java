package com.osypenko.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.osypenko.constant.NameMapping.*;

@Slf4j
@Controller
public class LoginController {
    @GetMapping(LOGIN)
    public String login() {
        return DIRECTORY_USER_PAGES + LOGIN;
    }
}