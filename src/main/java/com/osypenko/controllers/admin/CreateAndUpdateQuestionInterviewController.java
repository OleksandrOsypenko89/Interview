package com.osypenko.controllers.admin;

import com.osypenko.model.interview.QuestionInterview;
import com.osypenko.model.interview.Topic;
import com.osypenko.services.QuestionInterviewService;
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
public class CreateAndUpdateQuestionInterviewController {
    private final QuestionInterviewService questionInterviewService;

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
        questionInterview.setAnswer(updateQuestionInterview.getAnswer());
        questionInterview.setQuestion(updateQuestionInterview.getQuestion());
        questionInterview.setTopic(updateQuestionInterview.getTopic());
        return "redirect:/createandupdatequestion";
    }

    @PostMapping("/saveQuestionInterview")
    public String saveQuestionInterview(
            @SessionAttribute(name = "updateQuestionInterview") QuestionInterview questionInterview
    ) {
        questionInterviewService.save(questionInterview);
        return "redirect:/adminpage";
    }
}
