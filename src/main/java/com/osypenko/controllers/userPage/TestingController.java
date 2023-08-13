package com.osypenko.controllers.userPage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestingController {

    @GetMapping("/testing")
    public String getTesting() {
        return "userpages/testing";
    }
}
