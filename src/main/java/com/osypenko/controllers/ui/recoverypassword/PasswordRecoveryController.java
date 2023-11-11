package com.osypenko.controllers.ui.recoverypassword;

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
public class PasswordRecoveryController {
    private final MailService mailService;
    private final HttpSession session;
    private final UserService userService;

    @GetMapping(PASSWORD_RECOVERY)
    public String forgotPassword() {
        return DIRECTORY_PASSWORD_RECOVERY + PASSWORD_RECOVERY;
    }

    @PostMapping(CONFIRMATION_CODE)
    public String newPassword(String email) {
        Optional<User> optionalUser = userService.findByEmail(email);
        if (optionalUser.isEmpty()) {
            session.setAttribute(PASSWORD_FLAG, false);
            return REDIRECT + PASSWORD_RECOVERY;
        }

        int code = mailService.generatedRandomCode();
        session.setAttribute(CODE, code);
        session.setAttribute(EMAIL, email);
        session.removeAttribute(PASSWORD_FLAG);
        mailService.sendSimpleMessage(email, PASSWORD_CHANGE_CODE + code);
        return REDIRECT + CODE_PASSWORD_RECOVERY;
    }
}
