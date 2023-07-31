package com.osypenko.controllers;

import com.osypenko.services.MailService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ForgotPasswordController {

    private final MailService mailService;
    private final HttpSession session;

    @GetMapping("/forgotpassword")
    public String forgotPassword() {
        return "forgotpassword";
    }

    @PostMapping("/code")
    public String newPassword(String email) {
        int code = mailService.generatedRandomCode();
        session.setAttribute("code", code);
        session.setAttribute("email", email);
        mailService.sendSimpleMessage(email, "Код для зміни пароля - " + code);
        return "redirect:/code";
    }
}
