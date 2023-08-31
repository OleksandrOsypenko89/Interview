package com.osypenko.controllers.admin;

import com.osypenko.services.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TechnologiesController {
    private final MailService mailService;

    @GetMapping("/technologies")
    public String getTechnologies() {
        mailService.sendSimpleMessage("Oleksandrosipenk@gmail.com", "Вхід на сторінку з технологиями проекту.");
        return "admin/technologies";
    }
}
