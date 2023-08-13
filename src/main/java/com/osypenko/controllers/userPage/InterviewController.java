package com.osypenko.controllers.userPage;

import com.osypenko.model.interview.QuestionInterview;
import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.users.User;
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
    private final StatisticService statisticService;

    @GetMapping("/interview")
    public String getInterview(
            @SessionAttribute(name = "user") User user
            , @SessionAttribute(name = "know") Integer know
            , @SessionAttribute(name = "listQuestion") List<QuestionInterview> list
            , Statistic statistic
    ) {
        if (list.isEmpty()) {
            int percentage = (know * 100) / (int) session.getAttribute("size");

            statisticService.saveNewStatistic(user, statistic, percentage);

            user.getListQuestionInterviews().removeAll(user.getListQuestionInterviews());
            userService.createAndUpdateUser(user);
            return "redirect:/statistic";
        }
        session.setAttribute("question", list.get(0));
        return "userpages/interview";
    }

    @PostMapping("/knowAnswer")
    public String knowAnswer(
            @SessionAttribute(name = "know") Integer know
            , @SessionAttribute(name = "question") QuestionInterview questionInterview
            , @SessionAttribute(name = "listQuestion") List<QuestionInterview> list
    ) {
        list.remove(questionInterview);
        session.setAttribute("know", ++know);
        return "redirect:/interview";
    }

    @PostMapping("/noAnswer")
    public String noAnswer(
            @SessionAttribute(name = "user") User user
            , @SessionAttribute(name = "question") QuestionInterview questionInterview
            , @SessionAttribute(name = "listQuestion") List<QuestionInterview> list
    ) {
        Set<QuestionInterview> listStudyQuestion = user.getListStudyQuestion();
        listStudyQuestion.add(questionInterview);

        list.remove(questionInterview);
        return "redirect:/interview";
    }
}
