package com.osypenko.controller.template.interview;

import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.statistic.Type;
import com.osypenko.model.users.User;
import com.osypenko.services.interview.QuestionService;
import com.osypenko.services.statistics.StatisticService;
import com.osypenko.services.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.*;

import static com.osypenko.constant.Constant.SIZE_QUESTION_INTERVIEW;
import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final UserService userService;
    private final HttpSession session;
    private final StatisticService statisticService;
    private final QuestionService questionService;

    @GetMapping(QUESTION)
    public String getInterview(
            @SessionAttribute(USER) User user
            , @SessionAttribute(KNOW) Integer know
            , @SessionAttribute(LIST_QUESTION) List<QuestionInterview> listInterview
            , Statistic newStatisticsAdded
    ) {
        if (listInterview.isEmpty()) {
            int percentage = questionService.getPercentage(know, SIZE_QUESTION_INTERVIEW);

            statisticService.saveNewStatistic(user, newStatisticsAdded, percentage, know, SIZE_QUESTION_INTERVIEW, Type.QUESTIONS);

            user.getListQuestionInterviews().removeAll(user.getListQuestionInterviews());
            userService.saveAndFlushUser(user);

            session.setAttribute(NEW_STATISTICS_ADDED, newStatisticsAdded);
            return REDIRECT + STATISTIC;
        }
        session.setAttribute(QUESTION_INTERVIEW, listInterview.get(0));
        return DIRECTORY_INTERVIEW + QUESTION;
    }

    @PostMapping(KNOW_ANSWER)
    public String knowAnswer(
            @SessionAttribute(KNOW) Integer know
            , @SessionAttribute(QUESTION_INTERVIEW) QuestionInterview questionInterview
            , @SessionAttribute(LIST_QUESTION) List<QuestionInterview> list
    ) {
        list.remove(questionInterview);
        session.setAttribute(KNOW, ++know);
        return REDIRECT + QUESTION;
    }

    @PostMapping(NO_ANSWER)
    public String noAnswer(
            @SessionAttribute(USER) User user
            , @SessionAttribute(QUESTION_INTERVIEW) QuestionInterview questionInterview
            , @SessionAttribute(LIST_QUESTION) List<QuestionInterview> list
    ) {
        Set<QuestionInterview> listStudyQuestion = user.getListStudyQuestion();
        listStudyQuestion.add(questionInterview);

        list.remove(questionInterview);
        return REDIRECT + QUESTION;
    }
}
