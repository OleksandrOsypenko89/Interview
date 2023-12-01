package com.osypenko.services.admin;

import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.services.interview.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.osypenko.constant.Constant.FOLDER;
import static com.osypenko.constant.NameLogs.LOGS_FILE_NOT_FIND;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {
    private final QuestionService questionService;

    public QuestionInterview searchQuestion(String text) {
        List<QuestionInterview> all = questionService.getAll();
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

    public File[] getFolder() {
        File folder = new File(FOLDER);
        if (folder.exists()) {
            return folder.listFiles();
        }
        return null;
    }

    public String getDataLogsFile(String file) {
        try {
            Path path = Paths.get(file);
            byte[] fileBytes = Files.readAllBytes(path);
            return new String(fileBytes);
        } catch (IOException e) {
            log.error(LOGS_FILE_NOT_FIND);
            throw new RuntimeException(e);
        }
    }
}
