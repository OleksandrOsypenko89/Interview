package com.osypenko.controllers.recoverypassword;

import com.osypenko.model.users.User;
import com.osypenko.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import static com.osypenko.constant.NameMapping.*;
import static com.osypenko.constant.NameSessionAttributes.*;

@Controller
@RequiredArgsConstructor
public class NewPasswordController {
    private final UserService userService;

    @GetMapping(NEW_PASSWORD)
    public String newPassword() {
        return DIRECTORY_PASSWORD_RECOVERY + NEW_PASSWORD;
    }

    @PostMapping(BECK_TO_LOGIN)
    public String redirectLogin(
            @SessionAttribute(EMAIL) String email
            , String passwordOne
            , String passwordTwo
    ) {
        if (passwordOne.equals(passwordTwo)) {
            User user = userService.findByEmail(email);
            String hash = String.valueOf(passwordOne.hashCode());
            user.setPassword(hash);
            userService.createAndUpdateUser(user);
            return REDIRECT + SLASH;
        }
        return REDIRECT + NEW_PASSWORD;
    }
}
