package com.osypenko.controllers.interview;

import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.statistic.Type;
import com.osypenko.model.users.User;
import com.osypenko.services.statistics.StatisticService;
import com.osypenko.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final UserService userService;
    private final HttpSession session;
    private final StatisticService statisticService;

    @GetMapping("/question")
    public String getInterview(
            @SessionAttribute(name = "user") User user
            , @SessionAttribute(name = "know") Integer know
            , @SessionAttribute(name = "listQuestion") List<QuestionInterview> listInterview
            , Statistic newStatisticsAdded
    ) {
        if (listInterview.isEmpty()) {
            int sizeListQuestion = (int) session.getAttribute("sizeListQuestion");
            int percentage = (know * 100) /  sizeListQuestion;

            statisticService.saveNewStatistic(user, newStatisticsAdded, percentage, know, sizeListQuestion, Type.INTERVIEW);

            user.getListQuestionInterviews().removeAll(user.getListQuestionInterviews());
            userService.createAndUpdateUser(user);

            session.setAttribute("newStatisticsAdded", newStatisticsAdded);
            return "redirect:/statistic";
        }
        session.setAttribute("questionInterview", listInterview.get(0));
        return "userpages/question";
    }

    @PostMapping("/knowAnswer")
    public String knowAnswer(
            @SessionAttribute(name = "know") Integer know
            , @SessionAttribute(name = "questionInterview") QuestionInterview questionInterview
            , @SessionAttribute(name = "listQuestion") List<QuestionInterview> list
    ) {
        list.remove(questionInterview);
        session.setAttribute("know", ++know);
        return "redirect:/question";
    }

    @PostMapping("/noAnswer")
    public String noAnswer(
            @SessionAttribute(name = "user") User user
            , @SessionAttribute(name = "questionInterview") QuestionInterview questionInterview
            , @SessionAttribute(name = "listQuestion") List<QuestionInterview> list
    ) {
        Set<QuestionInterview> listStudyQuestion = user.getListStudyQuestion();
        listStudyQuestion.add(questionInterview);

        list.remove(questionInterview);
        return "redirect:/question";
    }
}
