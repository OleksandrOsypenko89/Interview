package com.osypenko.controllers.userPage;

import com.osypenko.model.interview.QuestionInterview;
import com.osypenko.model.users.User;
import com.osypenko.services.QuestionService;
import com.osypenko.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Set;

@Controller
@RequiredArgsConstructor
public class UserPageController {
    private final QuestionService questionService;
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/userpage")
    public String userPage() {
        return "userpages/userpage";
    }

    @PostMapping("/interview")
    public String interviewPage(
            @SessionAttribute(name = "user") User user
    ) {
        Set<QuestionInterview> questionInterviews = user.getListQuestionInterviews();
        if (questionInterviews.isEmpty()) {
            user.setListQuestionInterviews(questionService.createListQuestion());
            userService.createAndUpdateUser(user);
        }
        session.setAttribute("index", 0);
        session.setAttribute("know", 0);
        return "redirect:/interview";
    }

    @PostMapping("/delete")
    public String delete(
            @SessionAttribute(name = "user") User user
            , Integer id
    ) {
        questionService.deleteStudyQuestions(user, id);
        return "redirect:/userpage";
    }

    @PostMapping("/testing")
    public String testingPage() {
        return "redirect:/testing";
    }
}
