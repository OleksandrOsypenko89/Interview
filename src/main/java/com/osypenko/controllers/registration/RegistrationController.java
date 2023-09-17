package com.osypenko.controllers.registration;

import com.osypenko.model.users.User;
import com.osypenko.services.admin.MailService;
import com.osypenko.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.osypenko.constant.Constant.*;
import static com.osypenko.constant.NameMapping.*;
import static com.osypenko.constant.NameSessionAttributes.*;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final MailService mailService;
    private final HttpSession session;
    private final UserService userService;

    @GetMapping(REGISTRATION)
    public String registration() {
        session.removeAttribute(LOGIN_FLAG);
        return DIRECTORY_REGISTRATION + REGISTRATION;
    }

    @PostMapping(LOGIN)
    public String registrationNewUser(
            User user
            , String firstName
            , String lastName
            , String email
            , String password
    ) {
        if (userService.allEmailUsers().contains(email)) {
            session.setAttribute(REGISTRATION_FLAG, false);
            return REDIRECT + REGISTRATION;
        }

        String hashPassword = String.valueOf(password.hashCode());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(hashPassword);

        session.setAttribute(USER, user);

        int code = mailService.generatedRandomCode();
        session.setAttribute(CODE_REGISTRATION, code);
        session.removeAttribute(REGISTRATION_FLAG);
        mailService.sendSimpleMessage(email, TO_COMPLETE_THE_REGISTRATION_ENTER_THE_CODE + code);
        return REDIRECT + CODE_FOR_REGISTRATION;
    }
}
