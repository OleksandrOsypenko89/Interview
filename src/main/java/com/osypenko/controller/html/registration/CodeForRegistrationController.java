package com.osypenko.controller.html.registration;

import com.osypenko.model.users.User;
import com.osypenko.services.admin.MailService;
import com.osypenko.services.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import static com.osypenko.constant.Constant.*;
import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.*;

@Controller
@RequiredArgsConstructor
public class CodeForRegistrationController {
    private final UserService userService;
    private final MailService mailService;
    private final HttpSession session;

    @GetMapping(CODE_FOR_REGISTRATION)
    public String codeUser() {
        return DIRECTORY_REGISTRATION + CODE_FOR_REGISTRATION;
    }

    @PostMapping(NEW_USER)
    public String createNewUser(
            @SessionAttribute(USER) User user
            , @SessionAttribute(CODE_REGISTRATION) int codeSystem
            , int codeUser
    ) {
        if (codeSystem == codeUser) {
            userService.updateUser(user);
            session.setAttribute(NEW_USER_IS_REGISTERED, true);
            session.removeAttribute(CODE_NO_CORRECT);
            mailService.sendSimpleMessage(OLEKSANDR_GMAIL_COM, REGISTRATION_NEW_USER + user.getFirstName() + " " + user.getLastName());
            return REDIRECT + LOGIN;
        }
        session.setAttribute(CODE_NO_CORRECT, true);
        return REDIRECT + CODE_FOR_REGISTRATION;
    }
}
