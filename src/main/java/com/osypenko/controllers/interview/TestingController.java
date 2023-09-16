package com.osypenko.controllers.interview;

import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.statistic.Type;
import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.model.users.User;
import com.osypenko.services.statistics.StatisticService;
import com.osypenko.services.interview.TestingService;
import com.osypenko.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

import static com.osypenko.constant.NameMapping.*;
import static com.osypenko.constant.NameSessionAttributes.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestingController {
    private final UserService userService;
    private final HttpSession session;
    private final StatisticService statisticService;
    private final TestingService testingService;

    @GetMapping(TESTING)
    public String getTesting(
            @SessionAttribute(USER) User user
            , @SessionAttribute(KNOW) Integer know
            , @SessionAttribute(LIST_TESTING) List<TestingInterview> listTesting
            , Statistic newStatisticsAdded
    ) {
        if (listTesting.isEmpty()) {
            int sizeListQuestion = (int) session.getAttribute(SIZE_LIST_TESTING);
            int percentage = (know * 100) /  sizeListQuestion;

            statisticService.saveNewStatistic(user, newStatisticsAdded, percentage, know, sizeListQuestion, Type.TESTING);

            user.getListQuestionTesting().removeAll(user.getListQuestionTesting());
            userService.createAndUpdateUser(user);

            session.setAttribute(NEW_STATISTICS_ADDED, newStatisticsAdded);
            return REDIRECT + STATISTIC;
        }
        session.setAttribute(RANDOM_BUTTON, testingService.shuffleButtons(listTesting.get(0)));
        session.setAttribute(QUESTION_TESTING, listTesting.get(0));
        return DIRECTORY_USER_PAGES + TESTING;
    }

    @PostMapping(ANSWER_TESTING)
    public String answerTesting(
            @SessionAttribute(KNOW) Integer know
            , @SessionAttribute(QUESTION_TESTING) TestingInterview testingInterview
            , @SessionAttribute(LIST_TESTING) List<TestingInterview> list
            , String buttonAnswer
    ) {
        if (testingInterview.getCorrectAnswer().equals(buttonAnswer)) {
            session.setAttribute(KNOW, ++know);
        }
        list.remove(testingInterview);
        return REDIRECT + TESTING;
    }
}
