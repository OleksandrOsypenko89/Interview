package com.osypenko.controllers.userOperations;

import com.osypenko.model.users.User;
import com.osypenko.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    @GetMapping("/")
    public String login() {
        return "login";
    }
    @PostMapping("/userpage")
    public String getUserPage(String email, String password) {
        List<User> userList = userService.getAll();
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                String hash = String.valueOf(password.hashCode());
                if (user.getPassword().equals(hash)) {
                    return "redirect:/userpage";
                }
            }
        }
        return "redirect:/";
    }
}

