package com.osypenko.services;

import com.osypenko.model.interview.QuestionInterview;
import com.osypenko.model.testings.TestingInterview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserService userService;
    private final InterviewService interviewService;

    public int sizeUserList() {
        return userService.getAll().size();
    }

    public QuestionInterview searchQuestion(String text) {
        List<QuestionInterview> all = interviewService.getAll();
        for (QuestionInterview interview : all) {
            if (interview.getQuestion().equals(text) || interview.getId().toString().equals(text)) {
                return interview;
            }
        }
        return null;
    }

    public void changingFieldsQuestionInterview(QuestionInterview questionInterview, QuestionInterview updateQuestionInterview) {
        questionInterview.setAnswer(updateQuestionInterview.getAnswer());
        questionInterview.setQuestion(updateQuestionInterview.getQuestion());
        questionInterview.setTopic(updateQuestionInterview.getTopic());
    }

    public void changingFieldsTestingInterview(TestingInterview testingInterview, TestingInterview updateTestingInterview) {
        testingInterview.setQuestion(updateTestingInterview.getQuestion());
        testingInterview.setPicture(updateTestingInterview.getPicture());
        testingInterview.setFirstFalseAnswer(updateTestingInterview.getFirstFalseAnswer());
        testingInterview.setSecondFalseAnswer(updateTestingInterview.getSecondFalseAnswer());
        testingInterview.setThirdFalseAnswer(updateTestingInterview.getThirdFalseAnswer());
        testingInterview.setFourthFalseAnswer(updateTestingInterview.getFourthFalseAnswer());
        testingInterview.setFifthFalseAnswer(updateTestingInterview.getFifthFalseAnswer());
        testingInterview.setCorrectAnswer(updateTestingInterview.getCorrectAnswer());
        testingInterview.setAnswer(updateTestingInterview.getAnswer());
    }
}
