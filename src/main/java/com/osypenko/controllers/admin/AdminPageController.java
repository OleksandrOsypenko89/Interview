package com.osypenko.controllers.admin;

import com.osypenko.model.interview.QuestionInterview;
import com.osypenko.services.AdminService;
import com.osypenko.services.QuestionInterviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminPageController {
    private final AdminService adminService;
    private final QuestionInterviewService questionInterviewService;
    private final HttpSession session;

    @GetMapping("/adminpage")
    public String getAdminPage() {
        session.setAttribute("sizeUsers", adminService.sizeUserList());
        session.setAttribute("sizeAllQuestion", questionInterviewService.sizeAllQuestion());
        return "admin/adminpage";
    }

    @PostMapping("/adminQuestionInterview")
    public String adminQuestionInterview(String interview) {
        log.error("search question interview = " + interview);
        if (adminService.searchQuestion(interview) != null) {
            session.setAttribute("updateQuestion", adminService.searchQuestion(interview));
            return "redirect:/createandupdatequestion";
        }
        return "redirect:/adminpage";
    }

    @PostMapping("/adminNewQuestionInterview")
    public String adminNewQuestionInterview(QuestionInterview questionInterview) {
        session.setAttribute("updateQuestion", questionInterview);
        return "redirect:/createandupdatequestion";
    }

    @PostMapping("/adminQuestionTesting")
    public String adminQuestionTesting(String testing) {
        log.error("testing = " + testing);
        return "redirect:/adminpage";
    }

    @PostMapping("/adminNewQuestionTesting")
    public String adminNewQuestionTesting() {
        return "redirect:/adminpage";
    }
}
