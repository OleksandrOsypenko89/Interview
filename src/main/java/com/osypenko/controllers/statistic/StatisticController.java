package com.osypenko.controllers.statistic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.osypenko.constant.NameMapping.*;

@Slf4j
@Controller
public class StatisticController {

    @GetMapping(STATISTIC)
    public String statistic() {
        return DIRECTORY_STATISTIC + STATISTIC;
    }
}
