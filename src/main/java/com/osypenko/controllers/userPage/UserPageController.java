package com.osypenko.controllers.userPage;

import com.osypenko.model.users.User;
import com.osypenko.services.QuestionService;
import com.osypenko.services.StatisticService;
import com.osypenko.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
public class UserPageController {
    private final QuestionService questionService;
    private final StatisticService statisticService;
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/userpage")
    public String userPage(
            @SessionAttribute(name = "user") User user
    ) {
        statisticService.deletionOfOutdatedStatistics(user);
        return "userpages/userpage";
    }

    @PostMapping("/interview")
    public String interviewPage() {
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
        userService.createAndUpdateUser(user);
        return "redirect:/userpage";
    }

    @PostMapping("/testing")
    public String testingPage() {
        return "redirect:/testing";
    }
}
