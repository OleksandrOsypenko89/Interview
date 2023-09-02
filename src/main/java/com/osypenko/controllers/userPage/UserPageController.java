package com.osypenko.controllers.userPage;

import com.osypenko.exception.UserException;
import com.osypenko.model.interview.QuestionInterview;
import com.osypenko.model.users.User;
import com.osypenko.services.QuestionService;
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
public class UserPageController {
    private final QuestionService questionService;
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/userpage")
    public String userPage(
            @SessionAttribute(name = "userId") Long id
    ) {
        if (id == null) {
            return "login";
        }
        log.info("user id = " + id);
        User user = userService.getUser(id);
        questionService.sortStudyQuestion(user);
        session.setAttribute("user", user);
        return "userpages/userpage";
    }

    @PostMapping("/interview")
    public String interviewPage(
            @SessionAttribute(name = "user") User user
    ) {
        if (user.getListQuestionInterviews().isEmpty()) {
            user.setListQuestionInterviews(questionService.createListQuestion());
            try {
                userService.createAndUpdateUser(user);
            } catch (UserException e) {
                log.error("Перехват плавающей ошибки " + e);
            }
        }
        List<QuestionInterview> list = questionService.sortInterviewList(user);

        session.setAttribute("size", list.size());
        session.setAttribute("listQuestion", list);
        session.setAttribute("index", 0);
        session.setAttribute("know", 0);
        return "redirect:/interview";
    }

    @PostMapping("/delete")
    public String delete(
            @SessionAttribute(name = "user") User user
            , Integer idQuestion
    ) {
        questionService.deleteStudyQuestions(user, idQuestion);
        return "redirect:/userpage";
    }

    @PostMapping("/testing")
    public String testingPage() {
        return "redirect:/testing";
    }

    @PostMapping("/admin")
    public String adminPage() {
        return "redirect:/adminpage";
    }
}
