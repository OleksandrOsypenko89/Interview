package com.osypenko.services.admin;

import com.osypenko.BaseTests;
import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.interview.question.Topic;
import com.osypenko.model.interview.testings.TestingInterview;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;

class AdminServiceTest extends BaseTests {
    private final AdminService adminService;

    public AdminServiceTest(AdminService adminService) {
        this.adminService = adminService;
    }

    @Test
    void searchQuestion() {
        QuestionInterview searchQuestion = adminService.searchQuestion(QUESTION_INTERVIEW_QUESTION);
        Assertions.assertNotNull(searchQuestion);
        Assertions.assertEquals(ID_QUESTION_INTERVIEW, searchQuestion.getId());
        Assertions.assertEquals(QUESTION_INTERVIEW_QUESTION, searchQuestion.getQuestion());
        Assertions.assertEquals(QUESTION_INTERVIEW_ANSWER, searchQuestion.getAnswer());
        Assertions.assertEquals(Topic.SQL, searchQuestion.getTopic());

        QuestionInterview searchQuestionNull = adminService.searchQuestion(TEST_QUESTION);
        Assertions.assertNull(searchQuestionNull);
    }

    @Test
    void changingFieldsQuestionInterview() {
        QuestionInterview updateQuestionInterview = QuestionInterview.builder()
                .question(UPDATE_TEST_QUESTION)
                .answer(UPDATE_TEST_CORRECT_ANSWER)
                .topic(Topic.SPRING)
                .build();
        adminService.changingFieldsQuestionInterview(questionInterview, updateQuestionInterview);
        Assertions.assertEquals(updateQuestionInterview.getQuestion(), questionInterview.getQuestion());
        Assertions.assertEquals(updateQuestionInterview.getAnswer(), questionInterview.getAnswer());
        Assertions.assertEquals(updateQuestionInterview.getTopic(), questionInterview.getTopic());
        Assertions.assertNotEquals(updateQuestionInterview.getQuestion(), TEST_QUESTION);
    }

    @Test
    void changingFieldsTestingInterview() {
        TestingInterview updateTestingInterview = TestingInterview.builder()
                .question(UPDATE_TEST_QUESTION)
                .firstFalseAnswer(UPDATE_TEST_FIRST_FALSE_ANSWER)
                .secondFalseAnswer(UPDATE_TEST_SECOND_FALSE_ANSWER)
                .thirdFalseAnswer(UPDATE_TEST_THIRD_FALSE_ANSWER)
                .fourthFalseAnswer(UPDATE_TEST_FOURTH_FALSE_ANSWER)
                .fifthFalseAnswer(UPDATE_TEST_FIFTH_FALSE_ANSWER)
                .correctAnswer(UPDATE_TEST_CORRECT_ANSWER)
                .build();
        adminService.changingFieldsTestingInterview(testingInterview, updateTestingInterview);
        Assertions.assertEquals(updateTestingInterview.getQuestion(), testingInterview.getQuestion());
        Assertions.assertEquals(updateTestingInterview.getFirstFalseAnswer(), testingInterview.getFirstFalseAnswer());
        Assertions.assertEquals(updateTestingInterview.getSecondFalseAnswer(), testingInterview.getSecondFalseAnswer());
        Assertions.assertEquals(updateTestingInterview.getThirdFalseAnswer(), testingInterview.getThirdFalseAnswer());
        Assertions.assertEquals(updateTestingInterview.getFourthFalseAnswer(), testingInterview.getFourthFalseAnswer());
        Assertions.assertEquals(updateTestingInterview.getFifthFalseAnswer(), testingInterview.getFifthFalseAnswer());
        Assertions.assertEquals(updateTestingInterview.getCorrectAnswer(), testingInterview.getCorrectAnswer());
        Assertions.assertNotEquals(updateTestingInterview.getQuestion(), TEST_QUESTION);
    }

    @Test
    void getFolder() {
        File[] folder = adminService.getFolder();
        Assertions.assertNotNull(folder);
        Assertions.assertTrue(folder.length > 0);
    }

    @Test
    void getDataLogsFile() {
        String dataLogsFile = adminService.getDataLogsFile(TEST_FILE_LOG);
        Assertions.assertNotNull(dataLogsFile);
        Assertions.assertEquals(TEST_STR_LOG, dataLogsFile);
    }
}