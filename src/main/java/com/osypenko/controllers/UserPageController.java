package com.osypenko.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPageController {
    @GetMapping("/userpage")
    public String userPage() {
        return "userpage";
    }
}
