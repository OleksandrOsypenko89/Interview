package com.osypenko.controllers.userPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StatisticController {

    @GetMapping("/statistic")
    public String statistic() {
        return "userpages/statistic";
    }

    @PostMapping("/userPage")
    public String userPage() {
        return "redirect:/userpage";
    }
}
