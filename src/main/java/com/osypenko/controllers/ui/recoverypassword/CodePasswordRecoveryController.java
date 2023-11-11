package com.osypenko.controllers.ui.recoverypassword;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import static com.osypenko.constant.NameMapping.*;
import static com.osypenko.constant.NameSessionAttributes.*;

@Controller
@RequiredArgsConstructor
public class CodePasswordRecoveryController {
    private final HttpSession session;

    @GetMapping(CODE_PASSWORD_RECOVERY)
    public String code() {
        return DIRECTORY_PASSWORD_RECOVERY + CODE_PASSWORD_RECOVERY;
    }

    @PostMapping(NEW_PASSWORD)
    public String newPassword(
            @SessionAttribute(CODE) int codeSystem
            , int codeUser
    ) {
        if (codeSystem == codeUser) {
            session.removeAttribute(CODE_NO_CORRECT);
            return REDIRECT + NEW_PASSWORD;
        }
        session.setAttribute(CODE_NO_CORRECT, true);
        return REDIRECT + CODE_PASSWORD_RECOVERY;
    }
}
