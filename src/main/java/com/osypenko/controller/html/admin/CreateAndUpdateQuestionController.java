package com.osypenko.controller.html.admin;

import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.interview.question.Topic;
import com.osypenko.services.admin.AdminService;
import com.osypenko.services.interview.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameModel.ALL_TOPIC_INTERVIEW;
import static com.osypenko.constant.NameModel.MODEL_UPDATE_QUESTION_INTERVIEW;
import static com.osypenko.constant.NameSessionAttributes.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CreateAndUpdateQuestionController {
    private final QuestionService questionService;
    private final AdminService adminService;

    @GetMapping(CREATE_AND_UPDATE_QUESTION)
    public String createAndUpdateQuestion(
            @SessionAttribute(UPDATE_QUESTION_INTERVIEW) QuestionInterview questionInterview
            , Model model
    ) {
        model.addAttribute(MODEL_UPDATE_QUESTION_INTERVIEW, questionInterview);
        model.addAttribute(ALL_TOPIC_INTERVIEW, Topic.values());
        return DIRECTORY_ADMIN + CREATE_AND_UPDATE_QUESTION;
    }

    @PostMapping(VIEW_CHANGES_QUESTION)
    public String updateQuestionInterview(
            @SessionAttribute(UPDATE_QUESTION_INTERVIEW) QuestionInterview questionInterview
            , QuestionInterview updateQuestionInterview
    ) {
        adminService.changingFieldsQuestionInterview(questionInterview, updateQuestionInterview);
        return REDIRECT + CREATE_AND_UPDATE_QUESTION;
    }

    @PostMapping(SAVE_QUESTION_INTERVIEW)
    public String saveQuestionInterview(
            @SessionAttribute(UPDATE_QUESTION_INTERVIEW) QuestionInterview questionInterview
    ) {
        questionService.save(questionInterview);
        return REDIRECT + ADMIN_PAGE;
    }
}
