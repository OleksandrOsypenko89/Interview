package com.osypenko.controllers.registration;

import com.osypenko.model.users.User;
import com.osypenko.services.MailService;
import com.osypenko.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class CodeForRegistrationController {
    private final UserService userService;
    private final MailService mailService;

    @GetMapping("/codeforregistration")
    public String codeUser() {
        return "registration/codeforregistration";
    }

    @PostMapping("/newuser")
    public String createNewUser(
            @SessionAttribute(name = "user") User user
            , @SessionAttribute(name = "codeRegistration") int codeSystem
            , int codeUser
    ) {
        if (codeSystem == codeUser) {
            userService.createAndUpdateUser(user);
            userService.userHashMap().put(user.getEmail(), user.getId());
            mailService.sendSimpleMessage("Oleksandrosipenk@gmail.com", "Зареєстрований новий користувач " + user.getFirstName() + " " + user.getLastName());
            return "redirect:/";
        }
        return "redirect:/codeforregistration";
    }
}
