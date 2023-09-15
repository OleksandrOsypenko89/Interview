package com.osypenko.controllers.recoverypassword;

import com.osypenko.model.users.User;
import com.osypenko.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class NewPasswordController {
    private final UserService userService;

    @GetMapping("/newpassword")
    public String newPassword() {
        return "passwordrecovery/newpassword";
    }

    @PostMapping("/becktologin")
    public String redirectLogin(
            @SessionAttribute(name = "email") String email
            , String passwordOne
            , String passwordTwo
    ) {
        if (passwordOne.equals(passwordTwo)) {
            User user = userService.findByEmail(email);
            String hash = String.valueOf(passwordOne.hashCode());
            user.setPassword(hash);
            userService.createAndUpdateUser(user);
            return "redirect:/";
        }
        return "redirect:/newpassword";
    }
}
