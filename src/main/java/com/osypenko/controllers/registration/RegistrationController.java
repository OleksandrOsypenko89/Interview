package com.osypenko.controllers.registration;

import com.osypenko.model.users.User;
import com.osypenko.services.admin.MailService;
import com.osypenko.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.osypenko.constant.Constant.TO_COMPLETE_THE_REGISTRATION_ENTER_THE_CODE;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final MailService mailService;
    private final HttpSession session;
    private final UserService userService;

    @GetMapping("/registration")
    public String registration() {
        session.removeAttribute("loginFlag");
        return "registration/registration";
    }

    @PostMapping("/login")
    public String registrationNewUser(
            User user
            , String firstName
            , String lastName
            , String email
            , String password
    ) {
        Long id = userService.findByEmail(email).getId();
        if (id != null) {
            session.setAttribute("registrationFlag", false);
            return "redirect:/registration";
        }
        String hashPassword = String.valueOf(password.hashCode());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(hashPassword);

        session.setAttribute("user", user);

        int code = mailService.generatedRandomCode();
        session.setAttribute("codeRegistration", code);
        session.removeAttribute("registrationFlag");
        mailService.sendSimpleMessage(email, TO_COMPLETE_THE_REGISTRATION_ENTER_THE_CODE + code);
        return "redirect:/codeforregistration";
    }
}
