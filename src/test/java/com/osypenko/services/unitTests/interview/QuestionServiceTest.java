package com.osypenko.services.unitTests.interview;

import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.interview.question.Topic;
import com.osypenko.repository.QuestionInterviewRepository;
import com.osypenko.services.interview.QuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

    @Mock
    QuestionInterviewRepository questionInterviewRepository;
    @InjectMocks
    QuestionService questionService;

    private QuestionInterview questionInterviewOne;
    private QuestionInterview questionInterviewTwo;
    public static final int QUESTION_ID_ONE = 1;
    public static final String QUESTION_ONE = "question one";
    public static final String ANSWER_ONE = "answer one";
    public static final int QUESTION_ID_TWO = 2;
    public static final String QUESTION_TWO = "question two";
    public static final String ANSWER_TWO = "answer two";

    @BeforeEach
    void setup() {
        questionInterviewOne = QuestionInterview.builder()
                .id(QUESTION_ID_ONE)
                .question(QUESTION_ONE)
                .answer(ANSWER_ONE)
                .topic(Topic.CORE1)
                .build();
        questionInterviewTwo = QuestionInterview.builder()
                .id(QUESTION_ID_TWO)
                .question(QUESTION_TWO)
                .answer(ANSWER_TWO)
                .topic(Topic.CORE2)
                .build();
    }

    @Test
    void getAll() {
        Mockito.doAnswer(invocationOnMock -> List.of(questionInterviewOne, questionInterviewTwo))
                .when(questionInterviewRepository)
                .findAll();
        List<QuestionInterview> users = questionService.getAll();
        Assertions.assertEquals(2, users.size());
        Mockito.verify(questionInterviewRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(questionInterviewRepository);
    }

    @Test
    void get() {
        Mockito.doAnswer(invocationOnMock -> Optional.of(questionInterviewOne))
                .when(questionInterviewRepository)
                .findById(QUESTION_ID_ONE);
        QuestionInterview questionInterview = questionService.get(QUESTION_ID_ONE).orElseThrow();
        Assertions.assertEquals(questionInterviewOne.getId(), questionInterview.getId());
        Assertions.assertEquals(questionInterviewOne.getQuestion(), questionInterview.getQuestion());
        Assertions.assertEquals(questionInterviewOne.getAnswer(), questionInterview.getAnswer());
        Assertions.assertEquals(questionInterviewOne.getTopic(), questionInterview.getTopic());
        Mockito.verify(questionInterviewRepository, Mockito.times(1)).findById(QUESTION_ID_ONE);
        Mockito.verifyNoMoreInteractions(questionInterviewRepository);
    }

    @Test
    void save() {
        Mockito.doAnswer(invocationOnMock -> questionInterviewOne)
                .when(questionInterviewRepository)
                .save(questionInterviewOne);
        QuestionInterview questionInterview = questionService.save(questionInterviewOne);
        Assertions.assertEquals(questionInterviewOne.getId(), questionInterview.getId());
        Assertions.assertEquals(questionInterviewOne.getQuestion(), questionInterview.getQuestion());
        Assertions.assertEquals(questionInterviewOne.getAnswer(), questionInterview.getAnswer());
        Assertions.assertEquals(questionInterviewOne.getTopic(), questionInterview.getTopic());
        Mockito.verify(questionInterviewRepository, Mockito.times(1)).save(questionInterviewOne);
        Mockito.verifyNoMoreInteractions(questionInterviewRepository);
    }
}