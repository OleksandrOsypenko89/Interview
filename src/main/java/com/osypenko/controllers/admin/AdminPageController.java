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

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminPageController {
    private final AdminService adminService;
    private final QuestionService questionService;
    private final TestingService testingService;
    private final HttpSession session;

    @GetMapping("/adminpage")
    public String getAdminPage() {
        session.setAttribute("sizeUsers", adminService.sizeUserList());
        session.setAttribute("sizeAllQuestionInterview", questionService.sizeAllQuestion());
        session.setAttribute("sizeAllTestingInterview", testingService.sizeAllQuestion());
        return "admin/adminpage";
    }

    @PostMapping("/adminQuestionInterview")
    public String adminQuestionInterview(String interview) {
        log.error("search question interview = " + interview);
        if (adminService.searchQuestion(interview) != null) {
            session.setAttribute("updateQuestionInterview", adminService.searchQuestion(interview));
            return "redirect:/createandupdatequestion";
        }
        return "redirect:/adminpage";
    }

    @PostMapping("/adminNewQuestionInterview")
    public String adminNewQuestionInterview(QuestionInterview questionInterview) {
        session.setAttribute("updateQuestionInterview", questionInterview);
        return "redirect:/createandupdatequestion";
    }

    @PostMapping("/adminQuestionTesting")
    public String adminQuestionTesting(String testing) {
        log.error("search id testing = " + testing);
        int id = Integer.parseInt(testing);
        Optional<TestingInterview> optionalTestingInterview = testingService.get(id);
        if (optionalTestingInterview.isPresent()) {
            TestingInterview testingInterview = optionalTestingInterview.get();
            session.setAttribute("updateTestingInterview", testingInterview);
        }
        return "redirect:/createandupdatetesting";
    }

    @PostMapping("/adminNewQuestionTesting")
    public String adminNewQuestionTesting(TestingInterview testingInterview) {
        session.setAttribute("updateTestingInterview", testingInterview);
        return "redirect:/createandupdatetesting";
    }

    @PostMapping("/redirectAdminPage")
    public String redirectAdminPage() {
        return "redirect:/adminpage";
    }
}
