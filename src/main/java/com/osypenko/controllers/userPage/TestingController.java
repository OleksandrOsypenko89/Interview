package com.osypenko.controllers.userPage;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestingController {
    @GetMapping("/testing")
    public String getTesting() {
        return "userpages/testing";
    }
}
