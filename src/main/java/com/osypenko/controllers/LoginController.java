package com.osypenko.controllers;

import com.osypenko.model.users.User;
import com.osypenko.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/userpage")
    public String getUserPage(
            String email
            , String password
    ) {
        User user = userService.findByEmail(email);
        String hash = String.valueOf(password.hashCode());

        if (user.getPassword().equals(hash)) {
            session.setAttribute("userId", user.getId());
            session.removeAttribute("loginFlag");
            return "redirect:/userpage";
        }
        session.setAttribute("loginFlag", false);
        return "redirect:/";
    }
}

