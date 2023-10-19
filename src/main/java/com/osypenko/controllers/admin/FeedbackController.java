package com.osypenko.controllers.admin;

import com.osypenko.model.users.User;
import com.osypenko.services.admin.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import static com.osypenko.constant.Constant.OLEKSANDR_GMAIL_COM;
import static com.osypenko.constant.Constant.SENDER;
import static com.osypenko.constant.NameMapping.*;
import static com.osypenko.constant.NameSessionAttributes.USER;

@Controller
@RequiredArgsConstructor
public class FeedbackController {
    private final MailService mailService;

    @GetMapping(FEEDBACK)
    public String feedback() {
        return DIRECTORY_ADMIN + FEEDBACK;
    }

    @PostMapping(TEXT_ME)
    public String textMe(
            @SessionAttribute(USER) User user
            , String text
    ) {
        mailService.sendSimpleMessage(OLEKSANDR_GMAIL_COM, SENDER + user.getEmail() + "\n" + text);
        return REDIRECT + USER_PAGE;
    }
}
