package com.osypenko.controllers.recoverypassword;

import com.osypenko.services.MailService;
import com.osypenko.services.UserService;
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
    private final UserService userService;

    @GetMapping("/passwordrecovery")
    public String forgotPassword() {
        session.removeAttribute("loginFlag");
        return "passwordrecovery/passwordrecovery";
    }

    @PostMapping("/confirmationcode")
    public String newPassword(String email) {
        Long id = userService.hashMails().get(email);
        if (id != null) {
            int code = mailService.generatedRandomCode();
            session.setAttribute("code", code);
            session.setAttribute("email", email);
            session.removeAttribute("passwordFlag");
            mailService.sendSimpleMessage(email, PASSWORD_CHANGE_CODE + code);
            return "redirect:/codepasswordrecovery";
        }
        session.setAttribute("passwordFlag", false);
        return "redirect:/passwordrecovery";
    }
}
