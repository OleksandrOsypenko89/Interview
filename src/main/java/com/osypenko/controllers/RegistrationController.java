package com.osypenko.controllers;

import com.osypenko.model.users.User;
import com.osypenko.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;
    @GetMapping("/registration")
    public String registration(Model model, User user, String confirmPassword) {
        model.addAttribute("newUser", user);
        model.addAttribute("confirmPassword", confirmPassword);
        return "registration";
    }

    @PostMapping("/login")
    public String loginMewUser(User user, Model model) {
        String confirmPassword = (String) model.getAttribute("confirmPassword");
        if (user.getPassword().equals(confirmPassword)) {
            return "redirect:/registration";
        }
        userService.newUser(user);
        return "redirect:/";
    }
}
