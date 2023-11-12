package com.osypenko.controller.html.admin;

import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.services.admin.AdminService;
import com.osypenko.services.interview.TestingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameModel.MODEL_UPDATE_TESTING_INTERVIEW;
import static com.osypenko.constant.NameSessionAttributes.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CreateAndUpdateTestingController {
    private final TestingService testingService;
    private final AdminService adminService;

    @GetMapping(CREATE_AND_UPDATE_TESTING)
    public String createAndUpdateQuestion(
            @SessionAttribute(UPDATE_TESTING_INTERVIEW) TestingInterview testingInterview
            , Model model
    ) {
        model.addAttribute(MODEL_UPDATE_TESTING_INTERVIEW, testingInterview);
        return DIRECTORY_ADMIN + CREATE_AND_UPDATE_TESTING;
    }

    @PostMapping(VIEW_CHANGES_TESTING)
    public String updateTestingInterview(
            @SessionAttribute(UPDATE_TESTING_INTERVIEW) TestingInterview testingInterview
            , TestingInterview updateTestingInterview
    ) {
        adminService.changingFieldsTestingInterview(testingInterview, updateTestingInterview);
        return REDIRECT + CREATE_AND_UPDATE_TESTING;
    }

    @PostMapping(SAVE_TESTING_INTERVIEW)
    public String saveTestingInterview(
            @SessionAttribute(UPDATE_TESTING_INTERVIEW) TestingInterview testingInterview
    ) {
        testingService.save(testingInterview);
        return REDIRECT + ADMIN_PAGE;
    }
}
