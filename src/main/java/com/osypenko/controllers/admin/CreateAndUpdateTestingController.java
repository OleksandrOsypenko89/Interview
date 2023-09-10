package com.osypenko.controllers.admin;

import com.osypenko.model.testings.TestingInterview;
import com.osypenko.services.TestingService;
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
public class CreateAndUpdateTestingController {
    private final TestingService testingService;

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
        testingInterview.setPicture(updateTestingInterview.getPicture());
        testingInterview.setFirstFalseAnswer(updateTestingInterview.getFirstFalseAnswer());
        testingInterview.setSecondFalseAnswer(updateTestingInterview.getSecondFalseAnswer());
        testingInterview.setThirdFalseAnswer(updateTestingInterview.getThirdFalseAnswer());
        testingInterview.setFourthFalseAnswer(updateTestingInterview.getFourthFalseAnswer());
        testingInterview.setFifthFalseAnswer(updateTestingInterview.getFifthFalseAnswer());
        testingInterview.setCorrectAnswer(updateTestingInterview.getCorrectAnswer());
        testingInterview.setAnswer(updateTestingInterview.getAnswer());
        return "redirect:/createandupdatetesting";
    }

    @PostMapping("/saveTestingInterview")
    public String saveTestingInterview(
            @SessionAttribute(name = "updateTestingInterview") TestingInterview testingInterview
    ) {
        testingService.save(testingInterview);
        return "redirect:/adminpage";
    }
}
