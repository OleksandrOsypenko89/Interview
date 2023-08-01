package com.osypenko.controllers.userPage;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserPageController {
    private final HttpSession session;
    @GetMapping("/userpage")
    public String userPage() {
        session.setAttribute("userPageContext", session.getServletContext());
        return "userpage";
    }
}
