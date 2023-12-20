package com.osypenko.controller;

import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.model.users.User;
import com.osypenko.services.interview.QuestionService;
import com.osypenko.services.interview.TestingService;
import com.osypenko.services.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static com.osypenko.TestConstants.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class BaseMvcTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;
    @Autowired
    private TestingService testingService;
    @Autowired
    private QuestionService questionService;

    public User expectedUser;
    public User expectedAdmin;
    public TestingInterview testingInterview;
    public QuestionInterview questionInterview;

    @BeforeEach
    void setup() {
        expectedUser = userService.findByEmail(EXPECTED_USER_EMAIL).orElseThrow();
        expectedAdmin = userService.findByEmail(EXPECTED_ADMIN_MAIL).orElseThrow();
        testingInterview = testingService.get(1).orElseThrow();
        questionInterview = questionService.get(1).orElseThrow();
    }

    public ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }
}