package com.osypenko.services;

import com.osypenko.dto.UserDTO;
import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.interview.question.Topic;
import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.model.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import static com.osypenko.TestConstants.*;

@Transactional
@SpringBootTest
@TestConfiguration
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class BaseTests {
    public User newUser;
    public UserDTO userDTO;
    public User expectedUser;
    public UserDetails userDetails;
    public TestingInterview testingInterview;
    public QuestionInterview questionInterview;

    @BeforeEach
    void setup() {
        newUser = new User();
        expectedUser = User.builder()
                .id(EXPECTED_USER_ID)
                .email(EXPECTED_USER_EMAIL)
                .password(EXPECTED_USER_PASSWORD)
                .role(EXPECTED_USER_ROLE)
                .build();
        userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(EXPECTED_USER_EMAIL)
                .password(EXPECTED_USER_PASSWORD)
                .roles(EXPECTED_USER_ROLE.name())
                .build();
        userDTO = UserDTO.builder()
                .firstName(TEST_FIRST_NAME)
                .lastName(TEST_LAST_NAME)
                .email(TEST_EMAIL)
                .password(TEST_PASSWORD)
                .build();
        testingInterview = TestingInterview.builder()
                .question(TEST_QUESTION)
                .firstFalseAnswer(TEST_FIRST_FALSE_ANSWER)
                .secondFalseAnswer(TEST_SECOND_FALSE_ANSWER)
                .thirdFalseAnswer(TEST_THIRD_FALSE_ANSWER)
                .fourthFalseAnswer(TEST_FOURTH_FALSE_ANSWER)
                .fifthFalseAnswer(TEST_FIFTH_FALSE_ANSWER)
                .correctAnswer(TEST_ANSWER)
                .build();
        questionInterview = QuestionInterview.builder()
                .question(TEST_QUESTION)
                .answer(TEST_ANSWER)
                .topic(Topic.CORE1)
                .build();
    }
}