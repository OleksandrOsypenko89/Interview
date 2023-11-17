package com.osypenko.controller.rest.admin;

import com.osypenko.dto.InfoForAdmin;
import com.osypenko.services.interview.QuestionService;
import com.osypenko.services.interview.TestingService;
import com.osypenko.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.osypenko.constant.Endpoints.API;
import static com.osypenko.constant.Endpoints.INFO;

@RestController
@RequestMapping(API)
@RequiredArgsConstructor
public class AdminRestController {
    private final UserService userService;
    private final QuestionService questionService;
    private final TestingService testingService;

    @GetMapping(INFO)
    public ResponseEntity<InfoForAdmin> getAllInfoUser(InfoForAdmin infoForAdmin) {
        infoForAdmin.setRegisteredUsers(userService.getAll().size());
        infoForAdmin.setQuestionSize(questionService.sizeAllQuestion());
        infoForAdmin.setTestingSize(testingService.sizeAllQuestion());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(infoForAdmin);
    }
}
