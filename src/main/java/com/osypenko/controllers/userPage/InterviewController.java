package com.osypenko.controllers.userPage;

import com.osypenko.model.interview.QuestionInterview;
import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.users.User;
import com.osypenko.services.QuestionService;
import com.osypenko.services.StatisticService;
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
public class InterviewController {
    private final UserService userService;
    private final HttpSession session;
    private final QuestionService questionService;
    private final StatisticService statisticService;

    @GetMapping("/interview")
    public String getInterview(
            @SessionAttribute(name = "user") User user
            , Statistic statistic
    ) {
        Set<QuestionInterview> setQuestionInterviews = user.getListQuestionInterviews();
        List<QuestionInterview> list = new ArrayList<>(setQuestionInterviews);
        list.sort(Comparator
                .comparing(QuestionInterview::getTopic)
                .thenComparing(QuestionInterview::getId));

        int index = (Integer) session.getAttribute("index");
        if (index == setQuestionInterviews.size()) {
            int know = (int) session.getAttribute("know");
            int percentage = (know * 100) / setQuestionInterviews.size();

            statistic.setResult(percentage);
            statistic.setUserId(user.getId());
            statisticService.addStatistic(statistic);

            user.setListQuestionInterviews(questionService.createListQuestion());
            return "redirect:/userpage";
        }
        session.setAttribute("question", list.get(index));
        return "userpages/interview";
    }

    @PostMapping("/knowAnswer")
    public String knowAnswer(
            @SessionAttribute(name = "index") Integer index
            , @SessionAttribute(name = "know") Integer know
            ) {
        session.setAttribute("index", ++index);
        session.setAttribute("know", ++know);
        return "redirect:/interview";
    }

    @PostMapping("/noAnswer")
    public String noAnswer(
            @SessionAttribute(name = "user") User user
            , @SessionAttribute(name = "question") QuestionInterview questionInterview
            , @SessionAttribute(name = "index") Integer index
    ) {
        Set<QuestionInterview> listStudyQuestion = user.getListStudyQuestion();
        listStudyQuestion.add(questionInterview);

        session.setAttribute("index", ++index);
        userService.createAndUpdateUser(user);
        return "redirect:/interview";
    }
}
