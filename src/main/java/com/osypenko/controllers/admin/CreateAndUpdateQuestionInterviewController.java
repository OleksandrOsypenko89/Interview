package com.osypenko.controllers.admin;

import com.osypenko.model.interview.QuestionInterview;
import com.osypenko.model.interview.Topic;
import com.osypenko.services.QuestionService;
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
    private final QuestionService questionService;

    @GetMapping("/createandupdatequestion")
    public String createAndUpdateQuestion(
            @SessionAttribute(name = "updateQuestion") QuestionInterview questionInterview
            , Model model
    ) {
        model.addAttribute("idQuestion", questionInterview.getId());
        model.addAttribute("modelUpdateQuestion", questionInterview);
        model.addAttribute("allTopic", Topic.values());
        return "admin/createandupdatequestion";
    }

    @PostMapping("/update")
    public String update(
            @SessionAttribute(name = "updateQuestion") QuestionInterview questionInterview
            , QuestionInterview updateQuestionInterview
    ) {
        questionInterview.setAnswer(updateQuestionInterview.getAnswer());
        questionInterview.setQuestion(updateQuestionInterview.getQuestion());
        questionInterview.setTopic(updateQuestionInterview.getTopic());
        return "redirect:/createandupdatequestion";
    }

    @PostMapping("/saveNewQuestion")
    public String save(
            @SessionAttribute(name = "updateQuestion") QuestionInterview questionInterview
    ) {
        questionService.save(questionInterview);
        return "redirect:/createandupdatequestion";
    }

    @PostMapping("/deleteQuestion")
    public String delete(
            @SessionAttribute(name = "updateQuestion") QuestionInterview questionInterview
    ) {
        questionService.delete(questionInterview);
        return "redirect:/adminpage";
    }

    @PostMapping("/redirectAdminPage")
    public String redirectAdminPage() {
        return "redirect:/adminpage";
    }
}
