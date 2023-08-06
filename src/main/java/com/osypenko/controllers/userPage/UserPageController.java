package com.osypenko.controllers.userPage;

import com.osypenko.model.users.User;
import com.osypenko.services.QuestionService;
import com.osypenko.services.StatisticService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserPageController {
    private final HttpSession session;
    private final QuestionService questionService;
    private final StatisticService statisticService;
    @GetMapping("/userpage")
    public String userPage() {
        session.setAttribute("userPageContext", session.getServletContext());
        return "userpages/userpage";
    }

    @PostMapping("/interview")
    public String interviewPage() {
        User user = (User) session.getAttribute("user");
        questionService.fillingInAListWithAQuestionToTheUser(user);
        statisticService.deletionOfOutdatedStatistics(user);
        return "redirect:/interview";
    }

    @PostMapping("/testing")
    public String testingPage() {
        return "redirect:/testing";
    }
}
