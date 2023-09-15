package com.osypenko.controllers.userpage;

import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.model.users.User;
import com.osypenko.services.interview.QuestionService;
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
            @SessionAttribute(name = USER_ID) Long id
    ) {
        User user = userService.getUser(id);
        questionService.sortStudyQuestion(user);
        session.setAttribute(USER, user);
        session.setAttribute(KNOW, 0);


        log.info("user " + user);
        return DIRECTORY_USER_PAGES + USER_PAGE;
    }

    @PostMapping(QUESTION)
    public String questionPage(
            @SessionAttribute(name = USER) User user
    ) {
        if (user.getListQuestionInterviews().isEmpty()) {
            user.setListQuestionInterviews(questionService.createListQuestion());
            userService.createAndUpdateUser(user);
        }
        List<QuestionInterview> listQuestionInterviews = questionService.sortQuestionList(user);

        session.setAttribute(SIZE_LIST_QUESTION, listQuestionInterviews.size());
        session.setAttribute(LIST_QUESTION, listQuestionInterviews);
        session.setAttribute(KNOW, 0);
        return REDIRECT + QUESTION;
    }

    @PostMapping(TESTING)
    public String testingPage(
            @SessionAttribute(name = USER) User user
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

    @PostMapping(ALL_STATISTICS)
    public String allStatisticPage(
            @SessionAttribute(name = USER) User user
    ) {
        statisticService.deletionOfOutdatedStatistics(user);
        List<Statistic> list = statisticService.sortStatistic(user);
        session.setAttribute(GENERAL_RESULT, statisticService.result(user));
        session.setAttribute(STATISTIC_LIST, list);
        return REDIRECT + ALL_STATISTICS;
    }

    @PostMapping(ADMIN_PAGE)
    public String adminPage() {
        return REDIRECT + ADMIN_PAGE;
    }

    @PostMapping(DELETE_STUDY_QUESTION)
    public String deleteStudyQuestion(
            @SessionAttribute(name = USER) User user
            , Integer idQuestion
    ) {
        questionService.deleteStudyQuestions(user, idQuestion);
        return REDIRECT + USER_PAGE;
    }
}
