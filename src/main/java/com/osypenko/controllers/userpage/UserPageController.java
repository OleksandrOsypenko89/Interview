package com.osypenko.controllers.userpage;

import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.model.users.User;
import com.osypenko.services.interview.QuestionService;
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

import java.util.*;

import static com.osypenko.constant.NameMapping.*;
import static com.osypenko.constant.NameSessionAttributes.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserPageController {
    private final QuestionService questionService;
    private final TestingService testingService;
    private final UserService userService;
    private final StatisticService statisticService;
    private final HttpSession session;

    @GetMapping(USER_PAGE)
    public String userPage(
            @SessionAttribute(USER) User user
    ) {
        questionService.sortStudyQuestion(user);
        session.setAttribute(KNOW, 0);
        session.removeAttribute(REGISTRATION_FLAG);
        return DIRECTORY_USER_PAGES + USER_PAGE;
    }

    @GetMapping(QUESTION_PAGE)
    public String questionPage(
            @SessionAttribute(USER) User user
    ) {
        if (user.getListQuestionInterviews().isEmpty()) {
            user.setListQuestionInterviews(questionService.createListQuestion());
            userService.createAndUpdateUser(user);
        }
        List<QuestionInterview> listQuestionInterviews = questionService.sortQuestionList(user);

        session.setAttribute(SIZE_LIST_QUESTION, listQuestionInterviews.size());
        session.setAttribute(LIST_QUESTION, listQuestionInterviews);
        return REDIRECT + QUESTION;
    }

    @GetMapping(TESTING_PAGE)
    public String testingPage(
            @SessionAttribute(USER) User user
    ) {
        if (user.getListQuestionTesting().isEmpty()) {
            user.setListQuestionTesting(testingService.createListQuestion());
            userService.createAndUpdateUser(user);
        }
        List<TestingInterview> listQuestionTesting = testingService.sortTestingList(user);

        session.setAttribute(SIZE_LIST_TESTING, listQuestionTesting.size());
        session.setAttribute(LIST_TESTING, listQuestionTesting);
        return REDIRECT + TESTING;
    }

    @GetMapping(ALL_STATISTICS_PAGE)
    public String allStatisticPage(
            @SessionAttribute(USER) User user
    ) {
        statisticService.deletionOfOutdatedStatistics(user);
        List<Statistic> list = statisticService.sortStatistic(user);
        session.setAttribute(GENERAL_RESULT, statisticService.result(user));
        session.setAttribute(STATISTIC_LIST, list);
        return REDIRECT + ALL_STATISTICS;
    }

    @GetMapping(LOGOUT)
    public String logout() {
        session.removeAttribute(USER);
        return LOGIN;
    }

    @PostMapping(ADMIN_PAGE)
    public String adminPage() {
        return REDIRECT + ADMIN_PAGE;
    }

    @PostMapping(DELETE_STUDY_QUESTION)
    public String deleteStudyQuestion(
            @SessionAttribute(USER) User user
            , Integer idQuestion
    ) {
        questionService.deleteStudyQuestions(user, idQuestion);
        return REDIRECT + USER_PAGE;
    }
}
