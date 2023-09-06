package com.osypenko.controllers.admin;

import com.osypenko.model.interview.QuestionInterview;
import com.osypenko.model.interview.Topic;
import jakarta.servlet.http.HttpSession;
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
    private final HttpSession session;

    @GetMapping("/createandupdatequestion")
    public String createAndUpdateQuestion(Model model) {
        model.addAttribute("allTopic", Topic.values());
        return "admin/createandupdatequestion";
    }

    @PostMapping("/update")
    public String update(
            @SessionAttribute(name = "updateQuestion") QuestionInterview questionInterview,
            String updateQuestion
            , String updateAnswer
    ) {
        questionInterview.setQuestion(updateQuestion);
        questionInterview.setAnswer(updateAnswer);
        session.setAttribute("questionInterview", questionInterview);
        return "redirect:/createandupdatequestion";
    }

    @PostMapping("/save")
    public String save() {
        return "redirect:/createandupdatequestion";
    }

    @PostMapping("/redirectAdminPage")
    public String redirectAdminPage() {
        return "redirect:/adminpage";
    }
}
