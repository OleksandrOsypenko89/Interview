package com.osypenko.controller.html.statistic;

import com.osypenko.model.users.User;
import com.osypenko.services.statistics.StatisticService;
import com.osypenko.services.user.UserDetailsService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AllStatisticsController {
    private final HttpSession session;
    private final StatisticService statisticService;
    private final UserDetailsService userDetailsService;

    @GetMapping(ALL_STATISTICS)
    public String allStatistic(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userDetailsService.getUser(userDetails);
        if (user == null) {
            return LOGIN;
        }
        session.setAttribute(GENERAL_RESULT, statisticService.result(user));
        session.setAttribute(STATISTIC_LIST, statisticService.sortStatistic(user));
        return DIRECTORY_STATISTIC + ALL_STATISTICS;
    }
}
