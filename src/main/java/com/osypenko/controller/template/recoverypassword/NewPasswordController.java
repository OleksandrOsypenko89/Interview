package com.osypenko.controller.template.recoverypassword;

import com.osypenko.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NewPasswordController {
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
        return userService.userPasswordChange(email, passwordOne, passwordTwo);
    }
}
