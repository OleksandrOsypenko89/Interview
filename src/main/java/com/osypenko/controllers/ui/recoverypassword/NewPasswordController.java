package com.osypenko.controllers.ui.recoverypassword;

import com.osypenko.services.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import static com.osypenko.constant.NameMapping.*;
import static com.osypenko.constant.NameSessionAttributes.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NewPasswordController {
    private final HttpSession session;
    private final UserService userService;

    @GetMapping(NEW_PASSWORD)
    public String newPassword() {
        return DIRECTORY_PASSWORD_RECOVERY + NEW_PASSWORD;
    }

    @PostMapping(SAVE_NEW_PASSWORD)
    public String redirectLogin(
            @SessionAttribute(EMAIL) String email
            , String passwordOne
            , String passwordTwo
    ) {
        String newPasswordAccepted = userService.userPasswordChange(email, passwordOne, passwordTwo);
        if (newPasswordAccepted != null) {
            return newPasswordAccepted;
        }
        session.setAttribute(NEW_PASSWORD_FLAG, false);
        return REDIRECT + NEW_PASSWORD;
    }
}
