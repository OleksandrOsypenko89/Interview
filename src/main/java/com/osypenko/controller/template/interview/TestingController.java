package com.osypenko.controller.template.interview;

import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.statistic.Type;
import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.model.users.User;
import com.osypenko.services.statistics.StatisticService;
import com.osypenko.services.interview.TestingService;
import com.osypenko.services.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

import static com.osypenko.constant.Constant.SIZE_QUESTION;
import static com.osypenko.constant.Constant.ZERO;
import static com.osypenko.constant.Endpoints.*;
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
    ) {
        if (listTesting.isEmpty()) {
            int percentage = testingService.getPercentage(know, SIZE_QUESTION);

            Statistic newStatistic = statisticService.createNewStatistic(user, percentage, know, Type.TESTING);

            user.getListQuestionTesting().removeAll(user.getListQuestionTesting());
            userService.saveAndFlushUser(user);

            session.setAttribute(NEW_STATISTICS_ADDED, newStatistic);
            return REDIRECT + STATISTIC;
        }
        session.setAttribute(RANDOM_BUTTON, testingService.shuffleButtons(listTesting.get(ZERO)));
        session.setAttribute(QUESTION_TESTING, listTesting.get(ZERO));
        return DIRECTORY_INTERVIEW + TESTING;
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