package com.osypenko.controllers.statistic;

import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.users.User;
import com.osypenko.services.statistics.StatisticService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AllStatisticsController {
    private final StatisticService statisticService;
    private final HttpSession session;

    @GetMapping("/allstatistics")
    public String statistic(
            @SessionAttribute(name = "user") User user
    ) {
        statisticService.deletionOfOutdatedStatistics(user);
        List<Statistic> list = statisticService.sortStatistic(user);
        session.setAttribute("generalResult", statisticService.result(user));
        session.setAttribute("statisticList", list);
        return "statistic/allstatistics";
    }
}
