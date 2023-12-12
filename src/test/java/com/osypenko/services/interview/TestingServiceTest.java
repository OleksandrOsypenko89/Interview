package com.osypenko.services.interview;

import com.osypenko.services.BaseTests;
import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.model.users.User;
import com.osypenko.services.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.osypenko.constant.Constant.SIZE_QUESTION;

class TestingServiceTest extends BaseTests {
    private final TestingService testingService;
    private final UserService userService;

    public TestingServiceTest(TestingService testingService, UserService userService) {
        this.testingService = testingService;
        this.userService = userService;
    }

    @Test
    void getAll() {
        List<TestingInterview> all = testingService.getAll();
        Assertions.assertEquals(ALL_TESTING_INTERVIEW_SIZE, all.size());
    }

    @Test
    void get() {
        TestingInterview testingInterview = testingService.get(ID_TESTING_INTERVIEW).orElseThrow();
        Assertions.assertEquals(TESTING_INTERVIEW_QUESTION, testingInterview.getQuestion());
        Assertions.assertEquals(TESTING_INTERVIEW_FIRST_FALSE_ANSWER, testingInterview.getFirstFalseAnswer());
        Assertions.assertEquals(TESTING_INTERVIEW_CORRECT_ANSWER, testingInterview.getCorrectAnswer());
    }

    @Test
    void save() {
        TestingInterview saveTestingInterview = testingService.save(testingInterview);
        Assertions.assertEquals(TEST_QUESTION, saveTestingInterview.getQuestion());
        Assertions.assertEquals(TEST_FIRST_FALSE_ANSWER, saveTestingInterview.getFirstFalseAnswer());
        Assertions.assertEquals(TEST_SECOND_FALSE_ANSWER, saveTestingInterview.getSecondFalseAnswer());
        Assertions.assertEquals(TEST_THIRD_FALSE_ANSWER, saveTestingInterview.getThirdFalseAnswer());
        Assertions.assertEquals(TEST_FOURTH_FALSE_ANSWER, saveTestingInterview.getFourthFalseAnswer());
        Assertions.assertEquals(TEST_FIFTH_FALSE_ANSWER, saveTestingInterview.getFifthFalseAnswer());
        Assertions.assertEquals(TEST_ANSWER, saveTestingInterview.getCorrectAnswer());
    }

    @Test
    void shuffleButtons() {
        List<String> shuffleButtons = testingService.shuffleButtons(testingInterview);
        List<String> buttons = List.of(
                testingInterview.getFirstFalseAnswer()
                , testingInterview.getSecondFalseAnswer()
                , testingInterview.getThirdFalseAnswer()
                , testingInterview.getFourthFalseAnswer()
                , testingInterview.getFifthFalseAnswer()
                , testingInterview.getCorrectAnswer()
        );
        Assertions.assertNotEquals(buttons, shuffleButtons);
    }

    @Test
    void listFilling() {
        User user = userService.findByEmail(EXPECTED_USER_EMAIL).orElseThrow();
        Assertions.assertNotEquals(SIZE_QUESTION, user.getListQuestionTesting().size());
        List<TestingInterview> testingInterviews = testingService.listFilling(user);
        Assertions.assertEquals(SIZE_QUESTION, testingInterviews.size());
    }
}