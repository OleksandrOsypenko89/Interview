package com.osypenko.controllers.userPage;

import com.osypenko.model.interview.QuestionInterview;
import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.users.User;
import com.osypenko.services.StatisticService;
import com.osypenko.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class InterviewController {
    private final UserService userService;
    private final HttpSession session;
    private final StatisticService statisticService;

    @GetMapping("/interview")
    public String getInterview(
            @SessionAttribute(name = "user") User user
            , @SessionAttribute(name = "know") Integer know
            , Statistic statistic
    ) {
        Set<QuestionInterview> questionInterviews = user.getListQuestionInterviews();
        List<QuestionInterview> list = new ArrayList<>(questionInterviews);
        list.sort(Comparator
                .comparing(QuestionInterview::getTopic)
                .thenComparing(QuestionInterview::getId));

        if (questionInterviews.isEmpty()) {
            int percentage = (know * 100) / 15;

            statistic.setResult(percentage);
            statistic.setUserId(user.getId());
            statisticService.addStatistic(statistic);

            userService.createAndUpdateUser(user);
            return "redirect:/statistic";
        }
        session.setAttribute("question", list.get(0));
        return "userpages/interview";
    }

    @PostMapping("/knowAnswer")
    public String knowAnswer(
            @SessionAttribute(name = "know") Integer know
            , @SessionAttribute(name = "user") User user
            , @SessionAttribute(name = "question") QuestionInterview questionInterview
            ) {
        Set<QuestionInterview> questionInterviews = user.getListQuestionInterviews();
        questionInterviews.remove(questionInterview);
        session.setAttribute("know", ++know);
        return "redirect:/interview";
    }

    @PostMapping("/noAnswer")
    public String noAnswer(
            @SessionAttribute(name = "user") User user
            , @SessionAttribute(name = "question") QuestionInterview questionInterview
    ) {
        Set<QuestionInterview> listStudyQuestion = user.getListStudyQuestion();
        listStudyQuestion.add(questionInterview);

        Set<QuestionInterview> questionInterviews = user.getListQuestionInterviews();
        questionInterviews.remove(questionInterview);
        return "redirect:/interview";
    }
}
