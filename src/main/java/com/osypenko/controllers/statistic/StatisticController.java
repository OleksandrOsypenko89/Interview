package com.osypenko.controllers.statistic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.osypenko.constant.NameMapping.*;

@Controller
public class StatisticController {

    @GetMapping(STATISTIC)
    public String statistic() {
        return DIRECTORY_STATISTIC + STATISTIC;
    }
}
