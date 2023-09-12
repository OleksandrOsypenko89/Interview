package com.osypenko.controllers.userPage;

import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.statistic.Type;
import com.osypenko.model.testings.TestingInterview;
import com.osypenko.model.users.User;
import com.osypenko.services.StatisticService;
import com.osypenko.services.TestingService;
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
public class TestingController {
    private final UserService userService;
    private final HttpSession session;
    private final StatisticService statisticService;
    private final TestingService testingService;

    @GetMapping("/testing")
    public String getTesting(
            @SessionAttribute(name = "user") User user
            , @SessionAttribute(name = "knowTesting") Integer know
            , @SessionAttribute(name = "listTesting") List<TestingInterview> listTesting
            , Statistic statistic
    ) {
        if (listTesting.isEmpty()) {
            int sizeListQuestion = (int) session.getAttribute("sizeListTesting");
            int percentage = (know * 100) /  sizeListQuestion;

            statisticService.saveNewStatistic(user, statistic, percentage, know, sizeListQuestion, Type.TESTING);

            user.getListQuestionTesting().removeAll(user.getListQuestionTesting());
            userService.createAndUpdateUser(user);
            return "redirect:/statistic";
        }
        session.setAttribute("randomButton", testingService.shuffleButtons(listTesting.get(0)));
        session.setAttribute("questionTesting", listTesting.get(0));
        return "userpages/testing";
    }

    @PostMapping("/answerTesting")
    public String answerTesting(
            @SessionAttribute(name = "knowTesting") Integer know
            , @SessionAttribute(name = "questionTesting") TestingInterview testingInterview
            , @SessionAttribute(name = "listTesting") List<TestingInterview> list
            , String buttonAnswer
    ) {
        if (testingInterview.getCorrectAnswer().equals(buttonAnswer)) {
            session.setAttribute("knowTesting", ++know);
        }
        list.remove(testingInterview);
        return "redirect:/testing";
    }
}
