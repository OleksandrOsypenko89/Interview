package com.osypenko.controllers.admin;

import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.services.admin.AdminService;
import com.osypenko.services.interview.QuestionService;
import com.osypenko.services.interview.TestingService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

import static com.osypenko.constant.NameMapping.*;
import static com.osypenko.constant.NameSessionAttributes.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminPageController {

    private final AdminService adminService;
    private final QuestionService questionService;
    private final TestingService testingService;
    private final HttpSession session;

    @GetMapping(ADMIN_PAGE)
    public String getAdminPage() {
        session.setAttribute(SIZE_ALL_USERS, adminService.sizeUserList());
        session.setAttribute(SIZE_ALL_QUESTION_INTERVIEW, questionService.sizeAllQuestion());
        session.setAttribute(SIZE_ALL_TESTING_INTERVIEW, testingService.sizeAllQuestion());
        return DIRECTORY_ADMIN + ADMIN_PAGE;
    }

    @PostMapping(ADMIN_SEARCH_QUESTION)
    public String adminQuestionInterview(String interview) {
        if (adminService.searchQuestion(interview) != null) {
            session.setAttribute(UPDATE_QUESTION_INTERVIEW, adminService.searchQuestion(interview));
            return REDIRECT + CREATE_AND_UPDATE_QUESTION;
        }
        return REDIRECT + ADMIN_PAGE;
    }

    @PostMapping(ADMIN_NEW_QUESTION)
    public String adminNewQuestionInterview(QuestionInterview questionInterview) {
        session.setAttribute(UPDATE_QUESTION_INTERVIEW, questionInterview);
        return REDIRECT + CREATE_AND_UPDATE_QUESTION;
    }

    @PostMapping(ADMIN_SEARCH_TESTING)
    public String adminQuestionTesting(String testing) {
        int id = Integer.parseInt(testing);
        Optional<TestingInterview> optionalTestingInterview = testingService.get(id);
        if (optionalTestingInterview.isPresent()) {
            TestingInterview testingInterview = optionalTestingInterview.get();
            session.setAttribute(UPDATE_TESTING_INTERVIEW, testingInterview);
        }
        return REDIRECT + CREATE_AND_UPDATE_TESTING;
    }

    @PostMapping(ADMIN_NEW_TESTING)
    public String adminNewQuestionTesting(TestingInterview testingInterview) {
        session.setAttribute(UPDATE_TESTING_INTERVIEW, testingInterview);
        return REDIRECT + CREATE_AND_UPDATE_TESTING;
    }

    @PostMapping(REDIRECT_ADMIN_PAGE)
    public String redirectAdminPage() {
        return REDIRECT + ADMIN_PAGE;
    }
}
