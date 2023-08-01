package com.osypenko.controllers.userOperations.recoveryPassword;

import com.osypenko.services.MailService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.osypenko.constant.Constant.PASSWORD_CHANGE_CODE;

@Controller
@RequiredArgsConstructor
public class PasswordRecoveryController {

    private final MailService mailService;
    private final HttpSession session;

    @GetMapping("/passwordrecovery")
    public String forgotPassword() {
        return "passwordrecovery/passwordrecovery";
    }

    @PostMapping("/confirmationcode")
    public String newPassword(String email) {
        int code = mailService.generatedRandomCode();
        session.setAttribute("code", code);
        session.setAttribute("email", email);
        mailService.sendSimpleMessage(email, PASSWORD_CHANGE_CODE + code);
        return "redirect:/coderecoverypassword";
    }
}
