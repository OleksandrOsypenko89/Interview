package com.osypenko.controllers.userOperations.recoveryPassword;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CodeRecoveryPasswordController {

    private final HttpSession session;
    @GetMapping("/coderecoverypassword")
    public String code() {
        return "passwordrecovery/coderecoverypassword";
    }
    @PostMapping("/newpassword")
    public String newPassword(int codeUser) {
        int codeSystem = (int) session.getAttribute("code");

        if (codeSystem == codeUser) {
            return "redirect:/newpassword";
        }
        return "redirect:/coderecoverypassword";
    }
}
