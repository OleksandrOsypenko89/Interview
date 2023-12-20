package com.osypenko.services.interview;

import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.repository.TestingInterviewRepository;
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
class TestingServiceUnitTest {

    @Mock
    TestingInterviewRepository testingInterviewRepository;
    @InjectMocks
    TestingService testingService;

    private TestingInterview testingInterviewOne;
    private TestingInterview testingInterviewTwo;
    public static final int TESTING_ID_ONE = 1;
    public static final String TESTING_ONE = "testing one";
    public static final String CORRECT_ANSWER_ONE = "correct answer one";
    public static final String FIRST_FALSE_ANSWER_ONE = "first false answer one";
    public static final String SECOND_FALSE_ANSWER_ONE = "second false answer one";
    public static final int TESTING_ID_TWO = 2;
    public static final String TESTING_TWO = "testing two";
    public static final String CORRECT_ANSWER_TWO = "correct answer two";
    public static final String FIRST_FALSE_ANSWER_TWO = "first false answer two";
    public static final String SECOND_FALSE_ANSWER_TWO = "second false answer two";

    @BeforeEach
    void setup() {
        testingInterviewOne = TestingInterview.builder()
                .id(TESTING_ID_ONE)
                .question(TESTING_ONE)
                .firstFalseAnswer(FIRST_FALSE_ANSWER_ONE)
                .secondFalseAnswer(SECOND_FALSE_ANSWER_ONE)
                .correctAnswer(CORRECT_ANSWER_ONE)
                .build();
        testingInterviewTwo = TestingInterview.builder()
                .id(TESTING_ID_TWO)
                .question(TESTING_TWO)
                .firstFalseAnswer(FIRST_FALSE_ANSWER_TWO)
                .secondFalseAnswer(SECOND_FALSE_ANSWER_TWO)
                .correctAnswer(CORRECT_ANSWER_TWO)
                .build();
    }

    @Test
    void getAll() {
        Mockito.doAnswer(invocationOnMock -> List.of(testingInterviewOne, testingInterviewTwo))
                .when(testingInterviewRepository)
                .findAll();
        List<TestingInterview> users = testingService.getAll();
        Assertions.assertEquals(2, users.size());
        Mockito.verify(testingInterviewRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(testingInterviewRepository);
    }

    @Test
    void get() {
        Mockito.doAnswer(invocationOnMock -> Optional.of(testingInterviewOne))
                .when(testingInterviewRepository)
                .findById(TESTING_ID_ONE);
        TestingInterview testingInterview = testingService.get(TESTING_ID_ONE).orElseThrow();
        Assertions.assertEquals(testingInterviewOne.getId(), testingInterview.getId());
        Assertions.assertEquals(testingInterviewOne.getQuestion(), testingInterview.getQuestion());
        Assertions.assertEquals(testingInterviewOne.getFifthFalseAnswer(), testingInterview.getFifthFalseAnswer());
        Assertions.assertEquals(testingInterviewOne.getSecondFalseAnswer(), testingInterview.getSecondFalseAnswer());
        Assertions.assertEquals(testingInterviewOne.getAnswer(), testingInterview.getAnswer());
        Mockito.verify(testingInterviewRepository, Mockito.times(1)).findById(TESTING_ID_ONE);
        Mockito.verifyNoMoreInteractions(testingInterviewRepository);
    }

    @Test
    void save() {
        Mockito.doAnswer(invocationOnMock -> testingInterviewOne)
                .when(testingInterviewRepository)
                .save(testingInterviewOne);
        TestingInterview testingInterview = testingService.save(testingInterviewOne);
        Assertions.assertEquals(testingInterviewOne.getId(), testingInterview.getId());
        Assertions.assertEquals(testingInterviewOne.getQuestion(), testingInterview.getQuestion());
        Assertions.assertEquals(testingInterviewOne.getFifthFalseAnswer(), testingInterview.getFifthFalseAnswer());
        Assertions.assertEquals(testingInterviewOne.getSecondFalseAnswer(), testingInterview.getSecondFalseAnswer());
        Assertions.assertEquals(testingInterviewOne.getAnswer(), testingInterview.getAnswer());
        Mockito.verify(testingInterviewRepository, Mockito.times(1)).save(testingInterviewOne);
        Mockito.verifyNoMoreInteractions(testingInterviewRepository);
    }
}
