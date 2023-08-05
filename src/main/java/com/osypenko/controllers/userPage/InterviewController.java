package com.osypenko.controllers.userPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InterviewController {
    @GetMapping("/interview")
    public String getInterview() {
        return "userpages/interview";
    }
}
