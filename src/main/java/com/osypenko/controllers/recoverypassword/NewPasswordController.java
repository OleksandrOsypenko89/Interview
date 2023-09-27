package com.osypenko.controllers.recoverypassword;

import com.osypenko.config.SecurityConfig;
import com.osypenko.model.users.User;
import com.osypenko.services.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Optional;

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
        if (passwordOne.equals(passwordTwo)) {
            Optional<User> optionalUser = userService.findByEmail(email);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();

                String encode = SecurityConfig.PASSWORD_ENCODER.encode(passwordOne);

                user.setPassword(encode);
                userService.createAndUpdateUser(user);
                return REDIRECT + LOGIN;
            }
        }
        session.setAttribute(NEW_PASSWORD_FLAG, false);
        return REDIRECT + NEW_PASSWORD;
    }
}
