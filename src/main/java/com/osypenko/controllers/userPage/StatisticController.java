package com.osypenko.controllers.userPage;

import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.users.User;
import com.osypenko.services.StatisticService;
import com.osypenko.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class StatisticController {
    private final StatisticService statisticService;
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/statistic")
    public String statistic(
            @SessionAttribute(name = "userId") Long id
    ) {
        User user = userService.getUser(id);
        statisticService.deletionOfOutdatedStatistics(user);
        List<Statistic> list = statisticService.sortStatistic(user);
        session.setAttribute("generalResult", statisticService.result());
        session.setAttribute("statisticList", list);
        session.setAttribute("user", user);
        return "userpages/statistic";
    }

    @PostMapping("/userPage")
    public String userPage() {
        return "redirect:/userpage";
    }
}
