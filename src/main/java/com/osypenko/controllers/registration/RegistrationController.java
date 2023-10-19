package com.osypenko.controllers.registration;

import com.osypenko.model.users.User;
import com.osypenko.services.admin.MailService;
import com.osypenko.services.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

import static com.osypenko.constant.Constant.*;
import static com.osypenko.constant.NameMapping.*;
import static com.osypenko.constant.NameSessionAttributes.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final MailService mailService;
    private final HttpSession session;
    private final UserService userService;

    @GetMapping(REGISTRATION)
    public String registration() {
        return DIRECTORY_REGISTRATION + REGISTRATION;
    }

    @PostMapping(GET_REGISTRATION_CODE)
    public String registrationNewUser(
            User user
            , String firstName
            , String lastName
            , String email
            , String password
    ) {
        Optional<User> userOptional = userService.findByEmail(email);
        if (userOptional.isPresent()) {
            session.setAttribute(REGISTRATION_FLAG, false);
            return REDIRECT + REGISTRATION;
        }

        userService.createNewUser(user, firstName, lastName, email, password);
        session.setAttribute(USER, user);

        int code = mailService.generatedRandomCode();
        session.setAttribute(CODE_REGISTRATION, code);
        session.removeAttribute(REGISTRATION_FLAG);
        mailService.sendSimpleMessage(email, TO_COMPLETE_THE_REGISTRATION_ENTER_THE_CODE + code);
        return REDIRECT + CODE_FOR_REGISTRATION;
    }
}
