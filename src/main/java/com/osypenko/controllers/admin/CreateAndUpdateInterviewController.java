package com.osypenko.controllers.admin;

import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.interview.question.Topic;
import com.osypenko.services.admin.AdminService;
import com.osypenko.services.interview.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CreateAndUpdateInterviewController {
    private final QuestionService questionService;
    private final AdminService adminService;

    @GetMapping("/createandupdatequestion")
    public String createAndUpdateQuestion(
            @SessionAttribute(name = "updateQuestionInterview") QuestionInterview questionInterview
            , Model model
    ) {
        model.addAttribute("modelUpdateQuestionInterview", questionInterview);
        model.addAttribute("allTopicInterview", Topic.values());
        return "admin/createandupdatequestion";
    }

    @PostMapping("/updateQuestionInterview")
    public String updateQuestionInterview(
            @SessionAttribute(name = "updateQuestionInterview") QuestionInterview questionInterview
            , QuestionInterview updateQuestionInterview
    ) {
        adminService.changingFieldsQuestionInterview(questionInterview, updateQuestionInterview);
        return "redirect:/createandupdatequestion";
    }

    @PostMapping("/saveQuestionInterview")
    public String saveQuestionInterview(
            @SessionAttribute(name = "updateQuestionInterview") QuestionInterview questionInterview
    ) {
        questionService.save(questionInterview);
        return "redirect:/adminpage";
    }
}
