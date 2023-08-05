package com.osypenko.controllers.registration;

import com.osypenko.model.users.User;
import com.osypenko.services.MailService;
import com.osypenko.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CodeForRegistrationController {

    private final HttpSession session;
    private final UserService userService;
    private final MailService mailService;
    @GetMapping("/codeforregistration")
    public String codeUser() {
        return "registration/codeforregistration";
    }
    @PostMapping("/newuser")
    public String createNewUser(int codeUser) {
        int codeSystem = (int) session.getAttribute("codeRegistration");

        if (codeSystem == codeUser) {
            User user = (User) session.getAttribute("user");
            userService.createAndUpdateUser(user);
            userService.hashMails().put(user.getEmail(), user.getId());
            mailService.sendSimpleMessage("Oleksandrosipenk@gmail.com", "Зареєстрований новий користувач " + user.getFirstName() + " " + user.getLastName());
            return "redirect:/";
        }
        return "redirect:/codeforregistration";
    }
}
