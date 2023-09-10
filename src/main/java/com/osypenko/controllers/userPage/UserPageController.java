package com.osypenko.controllers.userPage;

import com.osypenko.model.interview.QuestionInterview;
import com.osypenko.model.testings.TestingInterview;
import com.osypenko.model.users.User;
import com.osypenko.services.InterviewService;
import com.osypenko.services.TestingService;
import com.osypenko.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserPageController {
    private final InterviewService interviewService;
    private final TestingService testingService;
    private final UserService userService;
    private final HttpSession session;

    @GetMapping("/userpage")
    public String userPage(
            @SessionAttribute(name = "userId") Long id
    ) {
        User user = userService.getUser(id);
        log.info("user " + user);
        interviewService.sortStudyQuestion(user);
        session.setAttribute("user", user);
        return "userpages/userpage";
    }

    @PostMapping("/interview")
    public String interviewPage(
            @SessionAttribute(name = "user") User user
    ) {
        if (user.getListQuestionInterviews().isEmpty()) {
            user.setListQuestionInterviews(interviewService.createListQuestion());
            userService.createAndUpdateUser(user);
        }
        List<QuestionInterview> list = interviewService.sortInterviewList(user);

        session.setAttribute("sizeListInterview", list.size());
        session.setAttribute("listInterview", list);
        session.setAttribute("index", 0);
        session.setAttribute("know", 0);
        return "redirect:/interview";
    }

    @PostMapping("/testing")
    public String testingPage(
            @SessionAttribute(name = "user") User user
    ) {
        if (user.getListQuestionTesting().isEmpty()) {
            user.setListQuestionTesting(testingService.createListQuestion());
            userService.createAndUpdateUser(user);
        }
        List<TestingInterview> list = testingService.sortInterviewList(user);

        session.setAttribute("sizeListTesting", list.size());
        session.setAttribute("listTesting", list);
        session.setAttribute("index", 0);
        session.setAttribute("know", 0);
        return "redirect:/testing";
    }

    @PostMapping("/delete")
    public String delete(
            @SessionAttribute(name = "user") User user
            , Integer idQuestion
    ) {
        interviewService.deleteStudyQuestions(user, idQuestion);
        return "redirect:/userpage";
    }

    @PostMapping("/admin")
    public String adminPage() {
        return "redirect:/adminpage";
    }
}
