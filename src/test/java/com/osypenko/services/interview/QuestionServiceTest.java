package com.osypenko.services.interview;

import com.osypenko.BaseTests;
import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.interview.question.Topic;
import com.osypenko.model.users.User;
import com.osypenko.services.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.osypenko.constant.Constant.SIZE_QUESTION;

class QuestionServiceTest extends BaseTests {
    private final QuestionService questionService;
    private final UserService userService;

    public QuestionServiceTest(QuestionService questionService, UserService userService) {
        this.questionService = questionService;
        this.userService = userService;
    }

    @Test
    void getAll() {
        List<QuestionInterview> questionInterviewList = questionService.getAll();
        Assertions.assertEquals(ALL_QUESTION_INTERVIEW_SIZE, questionInterviewList.size());
    }

    @Test
    void get() {
        QuestionInterview questionInterview = questionService.get(ID_QUESTION_INTERVIEW).orElseThrow();
        Assertions.assertEquals(QUESTION_INTERVIEW, questionInterview.getQuestion());
        Assertions.assertEquals(ANSWER_INTERVIEW, questionInterview.getAnswer());
        Assertions.assertEquals(Topic.SQL, questionInterview.getTopic());
    }

    @Test
    void save() {
        questionService.save(questionInterview);
        List<QuestionInterview> all = questionService.getAll();
        Assertions.assertEquals(ALL_QUESTION_INTERVIEW_SIZE + 1, all.size());
    }

    @Test
    void deleteStudyQuestions() {
        User user = userService.findByEmail(EXPECTED_USER_EMAIL).orElseThrow();
        questionService.deleteStudyQuestions(user, ID_QUESTION_INTERVIEW);
        Assertions.assertEquals(SIZE_LIST_STUDY_QUESTION_INTERVIEW_EXPECTED_USER - 1, user.getListStudyQuestion().size());
    }

    @Test
    void sortStudyQuestion() {
        User user = userService.findByEmail(EXPECTED_USER_EMAIL).orElseThrow();
        List<QuestionInterview> questionInterviews = questionService.sortStudyQuestion(user);
        Assertions.assertEquals(159, questionInterviews.get(0).getId());
        Assertions.assertEquals(275, questionInterviews.get(1).getId());
        Assertions.assertEquals(336, questionInterviews.get(2).getId());
    }

    @Test
    void listFilling() {
        User user = userService.findByEmail(EXPECTED_USER_EMAIL).orElseThrow();
        Assertions.assertNotEquals(SIZE_QUESTION, user.getListQuestionInterviews().size());
        List<QuestionInterview> questionInterviews = questionService.listFilling(user);
        Assertions.assertEquals(SIZE_QUESTION, questionInterviews.size());
    }
}