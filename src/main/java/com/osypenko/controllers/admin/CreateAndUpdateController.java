package com.osypenko.controllers.admin;

import com.osypenko.model.interview.QuestionInterview;
import com.osypenko.model.interview.Topic;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class CreateAndUpdateController {

    @GetMapping("/createandupdatequestion")
    public String createAndUpdateQuestion(
            @SessionAttribute(name = "updateQuestion")QuestionInterview questionInterview
            , Model model
            ) {
        model.addAttribute("question", questionInterview);
        model.addAttribute("allTopic", Topic.values());
        return "admin/createandupdatequestion";
    }
}
