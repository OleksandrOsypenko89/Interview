package com.osypenko.controllers.userOperations;

import com.osypenko.model.users.User;
import com.osypenko.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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
    public String getUserPage(String email, String password) {
        Long id = userService.hashMails().get(email);
        if (id != null) {
            Optional<User> optionalUser = userService.findById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                String hash = String.valueOf(password.hashCode());
                if (user.getPassword().equals(hash)) {
                    session.setAttribute("context", session.getServletContext());
                    session.removeAttribute("loginFlag");
                    return "redirect:/userpage";
                }
            }
        }
        session.setAttribute("loginFlag", false);
        return "redirect:/";
    }
}

