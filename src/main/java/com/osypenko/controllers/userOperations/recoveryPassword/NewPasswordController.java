package com.osypenko.controllers.userOperations.recoveryPassword;

import com.osypenko.model.users.User;
import com.osypenko.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
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

        List<User> all = userService.getAll();
        if (passwordOne.equals(passwordTwo)) {
            for (User user : all) {
                if (user.getEmail().equals(email)) {
                    String hash = String.valueOf(passwordOne.hashCode());

                    user.setPassword(hash);
                    userService.createAndUpdateUser(user);
                    return "redirect:/";
                }
            }
        }
        return "redirect:/newpassword";
    }
}
