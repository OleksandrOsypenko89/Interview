package com.osypenko.controller.html.userpage;

import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.model.users.User;
import com.osypenko.services.interview.QuestionService;
import com.osypenko.services.interview.TestingService;
import com.osypenko.services.user.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.*;

import static com.osypenko.constant.Constant.ZERO;
import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserPageController {
    private final QuestionService questionService;
    private final TestingService testingService;
    private final UserService userService;
    private final HttpSession session;

    @GetMapping(USER_PAGE)
    public String userPage(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUser(userDetails);
        if (user == null) {
            return LOGIN;
        }
        session.setAttribute(USER, user);
        questionService.sortStudyQuestion(user);
        session.removeAttribute(REGISTRATION_FLAG);
        return DIRECTORY_USER_PAGES + USER_PAGE;
    }

    @GetMapping(QUESTION_PAGE)
    public String questionPage(@SessionAttribute(USER) User user) {
        List<QuestionInterview> listQuestionInterviews = questionService.listFilling(user);
        session.setAttribute(SIZE_LIST_QUESTION, listQuestionInterviews.size());
        session.setAttribute(LIST_QUESTION, listQuestionInterviews);
        session.setAttribute(KNOW, ZERO);
        return REDIRECT + QUESTION;
    }

    @GetMapping(TESTING_PAGE)
    public String testingPage(@SessionAttribute(USER) User user) {
        List<TestingInterview> listQuestionTesting = testingService.listFilling(user);
        session.setAttribute(SIZE_LIST_TESTING, listQuestionTesting.size());
        session.setAttribute(LIST_TESTING, listQuestionTesting);
        session.setAttribute(KNOW, ZERO);
        return REDIRECT + TESTING;
    }

    @GetMapping(ALL_STATISTICS_PAGE)
    public String allStatisticPage() {
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
