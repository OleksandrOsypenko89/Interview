package com.osypenko.controller;

import com.osypenko.model.users.User;
import com.osypenko.services.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class BaseMvcTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;

    public User user;
    public User admin;

    public final String USER_MAIL = "demo_test@gmail.com";
    public final String ADMIN_MAIL = "Johnny_Depp@gmail.com";
    public static final int ALL_STATISTIC_SIZE = 2;
    public static final int SIZE_LIST_STUDY_QUESTION_INTERVIEW_USER = 3;
    public static final int STUDY_QUESTION_ID_159 = 159;
    public static final int ALL_USERS_FINAL_SIZE = 22;
    public static final int REGISTRATION_CODE = 123456;
    public static final int WRONG_REGISTRATION_CODE = 654321;
    public static final String TEST_FIRST_NAME = "test_first_name";
    public static final String TEST_LAST_NAME = "test_last_name";
    public static final String TEST_EMAIL = "test_mail@gmail.com";
    public static final String TEST_PASSWORD = "test_password";
    public static final String PASSWORD_ONE = "passwordOne";
    public static final String PASSWORD_TWO = "passwordTwo";
    public static final String BUTTON_ANSWER = "YES";
    public static final int SIZE_LIST_STUDY_QUESTION_INTERVIEW_EXPECTED_USER = 3;
    public static final String LOG_FILE_ADDRESS = "src/test/resources/logs/log-file.log";
    public static final String TEST_TEXT = "test text";

    @BeforeEach
    void setup() {
        user = userService.findByEmail(USER_MAIL).orElseThrow();
        admin = userService.findByEmail(ADMIN_MAIL).orElseThrow();
    }

    public ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }
}
