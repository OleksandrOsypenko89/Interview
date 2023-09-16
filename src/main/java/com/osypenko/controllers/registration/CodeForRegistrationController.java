package com.osypenko.controllers.registration;

import com.osypenko.model.users.User;
import com.osypenko.services.admin.MailService;
import com.osypenko.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import static com.osypenko.constant.Constant.*;
import static com.osypenko.constant.NameMapping.*;
import static com.osypenko.constant.NameSessionAttributes.*;

@Controller
@RequiredArgsConstructor
public class CodeForRegistrationController {
    private final UserService userService;
    private final MailService mailService;

    @GetMapping(CODE_FOR_REGISTRATION)
    public String codeUser() {
        return REDIRECT + CODE_FOR_REGISTRATION;
    }

    @PostMapping(NEW_USER)
    public String createNewUser(
            @SessionAttribute(USER) User user
            , @SessionAttribute(CODE_REGISTRATION) int codeSystem
            , int codeUser
    ) {
        if (codeSystem == codeUser) {
            userService.createAndUpdateUser(user);
            mailService.sendSimpleMessage(OLEKSANDR_GMAIL_COM, REGISTRATION_NEW_USER + user.getFirstName() + " " + user.getLastName());
            return REDIRECT + SLASH;
        }
        return REDIRECT + CODE_FOR_REGISTRATION;
    }
}
