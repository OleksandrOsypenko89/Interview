package com.osypenko.controllers;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CheckTheCodeController {

    private final HttpSession session;
    @GetMapping("/code")
    public String code() {
        return "сheckthecode";
    }
    @PostMapping("/newpassword")
    public String newPassword(int codeUser) {
        int codeSession = (int) session.getAttribute("code");
        if (codeSession == codeUser) {
            return "redirect:/newpassword";
        }
        return "redirect:/code";
    }
}
