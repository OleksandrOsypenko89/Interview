package com.osypenko.services;

import com.osypenko.model.interview.QuestionInterview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserService userService;
    private final QuestionInterviewService questionInterviewService;

    public int sizeUserList() {
        return userService.getAll().size();
    }

    public QuestionInterview searchQuestion(String text) {
        List<QuestionInterview> all = questionInterviewService.getAll();
        for (QuestionInterview interview : all) {
            if (interview.getQuestion().equals(text) || interview.getId().toString().equals(text)) {
                return interview;
            }
        }
        return null;
    }
}
