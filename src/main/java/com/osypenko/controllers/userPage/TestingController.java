package com.osypenko.controllers.userPage;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TestingController {
    private final HttpSession session;
    @GetMapping("/testing")
    public String getTesting() {
        session.setAttribute("userPageContext", session.getServletContext());
        return "userpages/testing";
    }
}
