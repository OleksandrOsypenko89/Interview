package com.osypenko.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NewPasswordController {
    @GetMapping("/newpassword")
    public String newPassword() {
        return "newpassword";
    }
    @PostMapping("/becktologin")
    public String redirectLogin() {
        return "redirect:/";
    }
}
