package com.osypenko.controllers;

import com.osypenko.model.users.User;
import com.osypenko.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.osypenko.constant.NameMapping.*;
import static com.osypenko.constant.NameSessionAttributes.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping(SLASH)
    public String login() {
        return LOGIN;
    }

    @PostMapping(USER_PAGE)
    public String getUserPage(
            String email
            , String password
    ) {
        session.removeAttribute(NEW_USER_IS_REGISTERED);
        session.setAttribute(LOGIN_FLAG, false);

        if (!userService.allEmailUsers().contains(email)) {
            return REDIRECT + SLASH;
        }

        User user = userService.findByEmail(email);
        String hash = String.valueOf(password.hashCode());

        if (user.getPassword().equals(hash)) {
            session.setAttribute(USER_ID, user.getId());
            session.removeAttribute(LOGIN_FLAG);
            return REDIRECT + USER_PAGE;
        }

        return REDIRECT + SLASH;
    }
}

