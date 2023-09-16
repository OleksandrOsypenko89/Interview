package com.osypenko.controllers.recoverypassword;

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
public class PasswordRecoveryController {
    private final MailService mailService;
    private final HttpSession session;
    private final UserService userService;

    @GetMapping(PASSWORD_RECOVERY)
    public String forgotPassword() {
        session.removeAttribute(LOGIN_FLAG);
        return DIRECTORY_PASSWORD_RECOVERY + PASSWORD_RECOVERY;
    }

    @PostMapping(CONFIRMATION_CODE)
    public String newPassword(String email) {
        Long id = userService.findByEmail(email).getId();
        if (id != null) {
            int code = mailService.generatedRandomCode();
            session.setAttribute(CODE, code);
            session.setAttribute(EMAIL, email);
            session.removeAttribute(PASSWORD_FLAG);
            mailService.sendSimpleMessage(email, PASSWORD_CHANGE_CODE + code);
            return REDIRECT + CODE_PASSWORD_RECOVERY;
        }
        session.setAttribute(PASSWORD_FLAG, false);
        return REDIRECT + PASSWORD_RECOVERY;
    }
}
