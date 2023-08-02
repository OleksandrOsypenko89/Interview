package com.osypenko.controllers.exeption;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotAuthorizedController {

    @GetMapping("/notauthorized")
    public String notAuthorized() {
        return "notauthorized/notauthorized";
    }
}
