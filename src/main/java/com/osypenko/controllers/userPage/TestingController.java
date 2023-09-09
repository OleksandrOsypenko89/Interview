package com.osypenko.controllers.userPage;

import com.osypenko.model.testings.TestingInterview;
import com.osypenko.services.TestingInterviewService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class TestingController {
    private final TestingInterviewService testingInterviewService;
    private final HttpSession session;

    @GetMapping("/testing")
    public String getTesting() {
        List<TestingInterview> all = testingInterviewService.getAll();
        session.setAttribute("all", all);
        return "userpages/testing";
    }
}
