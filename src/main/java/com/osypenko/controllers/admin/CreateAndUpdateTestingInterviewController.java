package com.osypenko.controllers.admin;

import com.osypenko.model.testings.TestingInterview;
import com.osypenko.services.TestingInterviewService;
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
public class CreateAndUpdateTestingInterviewController {
    private final TestingInterviewService testingInterviewService;

    @GetMapping("/createandupdatetesting")
    public String createAndUpdateQuestion(
            @SessionAttribute(name = "updateTestingInterview") TestingInterview testingInterview
            , Model model
    ) {
        model.addAttribute("modelUpdateTestingInterview", testingInterview);
        return "admin/createandupdatetesting";
    }

    @PostMapping("/updateTestingInterview")
    public String updateTestingInterview(
            @SessionAttribute(name = "updateTestingInterview") TestingInterview testingInterview
            , TestingInterview updateTestingInterview
    ) {
        testingInterview.setQuestion(updateTestingInterview.getQuestion());
        testingInterview.setFirstIncorrectAnswer(updateTestingInterview.getFirstIncorrectAnswer());
        testingInterview.setSecondIncorrectAnswer(updateTestingInterview.getSecondIncorrectAnswer());
        testingInterview.setThirdIncorrectAnswer(updateTestingInterview.getThirdIncorrectAnswer());
        testingInterview.setCorrectAnswer(updateTestingInterview.getCorrectAnswer());
        testingInterview.setAnswer(updateTestingInterview.getAnswer());
        return "redirect:/createandupdatetesting";
    }

    @PostMapping("/saveTestingInterview")
    public String saveTestingInterview(
            @SessionAttribute(name = "updateTestingInterview") TestingInterview testingInterview
    ) {
        testingInterviewService.save(testingInterview);
        return "redirect:/adminpage";
    }
}
