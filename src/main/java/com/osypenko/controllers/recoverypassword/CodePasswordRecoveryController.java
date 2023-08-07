package com.osypenko.controllers.recoverypassword;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class CodePasswordRecoveryController {

    @GetMapping("/codepasswordrecovery")
    public String code() {
        return "passwordrecovery/codepasswordrecovery";
    }

    @PostMapping("/newpassword")
    public String newPassword(
            @SessionAttribute(name = "code") int codeSystem
            , int codeUser
    ) {
        if (codeSystem == codeUser) {
            return "redirect:/newpassword";
        }
        return "redirect:/codepasswordrecovery";
    }
}
