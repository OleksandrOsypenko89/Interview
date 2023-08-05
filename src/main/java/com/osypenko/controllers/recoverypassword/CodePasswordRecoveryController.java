package com.osypenko.controllers.recoverypassword;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CodePasswordRecoveryController {

    private final HttpSession session;
    @GetMapping("/codepasswordrecovery")
    public String code() {
        return "passwordrecovery/codepasswordrecovery";
    }
    @PostMapping("/newpassword")
    public String newPassword(int codeUser) {
        int codeSystem = (int) session.getAttribute("code");

        if (codeSystem == codeUser) {
            return "redirect:/newpassword";
        }
        return "redirect:/codepasswordrecovery";
    }
}
