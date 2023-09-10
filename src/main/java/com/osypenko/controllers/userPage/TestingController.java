package com.osypenko.controllers.userPage;

import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.statistic.Type;
import com.osypenko.model.testings.TestingInterview;
import com.osypenko.model.users.User;
import com.osypenko.services.StatisticService;
import com.osypenko.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestingController {
    private final UserService userService;
    private final HttpSession session;
    private final StatisticService statisticService;

    @GetMapping("/testing")
    public String getTesting(
            @SessionAttribute(name = "user") User user
            , @SessionAttribute(name = "know") Integer know
            , @SessionAttribute(name = "listTesting") List<TestingInterview> list
            , Statistic statistic
            , HashSet<String> randomButton
    ) {
        if (list.isEmpty()) {
            int sizeListQuestion = (int) session.getAttribute("sizeListTesting");
            int percentage = (know * 100) /  sizeListQuestion;

            statisticService.saveNewStatistic(user, statistic, percentage, know, sizeListQuestion, Type.TESTING);

            user.getListQuestionTesting().removeAll(user.getListQuestionTesting());
            userService.createAndUpdateUser(user);
            return "redirect:/statistic";
        }
        session.setAttribute("questionTesting", list.get(0));
        randomButton.add(list.get(0).getCorrectAnswer());
        randomButton.add(list.get(0).getFirstFalseAnswer());
        randomButton.add(list.get(0).getSecondFalseAnswer());
        randomButton.add(list.get(0).getThirdFalseAnswer());
        randomButton.add(list.get(0).getFourthFalseAnswer());
        randomButton.add(list.get(0).getFifthFalseAnswer());
        session.setAttribute("randomButton", randomButton);
        return "userpages/testing";
    }
}
