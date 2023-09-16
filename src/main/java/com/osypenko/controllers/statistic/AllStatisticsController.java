package com.osypenko.controllers.statistic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.osypenko.constant.NameMapping.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AllStatisticsController {

    @GetMapping(ALL_STATISTICS)
    public String allStatistic() {
        return DIRECTORY_STATISTIC + ALL_STATISTICS;
    }
}
