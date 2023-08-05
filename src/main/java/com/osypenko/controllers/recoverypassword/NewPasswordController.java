package com.osypenko.controllers.recoverypassword;

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
public class NewPasswordController {

    private final HttpSession session;
    private final UserService userService;

    @GetMapping("/newpassword")
    public String newPassword() {
        return "passwordrecovery/newpassword";
    }

    @PostMapping("/becktologin")
    public String redirectLogin(String passwordOne, String passwordTwo) {
        String email = (String) session.getAttribute("email");

        if (passwordOne.equals(passwordTwo)) {
            Long id = userService.hashMails().get(email);
            Optional<User> optionalUser = userService.findById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                String hash = String.valueOf(passwordOne.hashCode());
                user.setPassword(hash);
                userService.createAndUpdateUser(user);
                return "redirect:/";
            }
        }
        return "redirect:/newpassword";
    }
}
