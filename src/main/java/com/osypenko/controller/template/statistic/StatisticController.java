package com.osypenko.controller.template.statistic;

import com.osypenko.model.users.User;
import com.osypenko.services.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.USER;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StatisticController {
    private final UserService userService;
    private final HttpSession session;

    @GetMapping(STATISTIC)
    public String statistic(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUser(userDetails);
        session.setAttribute(USER, user);
        return DIRECTORY_STATISTIC + STATISTIC;
    }
}
