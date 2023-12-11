package com.osypenko;

import com.osypenko.dto.UserDTO;
import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.interview.question.Topic;
import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.model.users.Role;
import com.osypenko.model.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class BaseTests {
    public User newUser;
    public UserDTO userDTO;
    public User expectedUser;
    public UserDetails userDetails;
    public TestingInterview testingInterview;
    public QuestionInterview questionInterview;

    public static final long EXPECTED_USER_ID = 4L;
    public static final String EXPECTED_USER_EMAIL = "demo@gmail.com";
    public static final String EXPECTED_USER_PASSWORD = "{bcrypt}$2a$10$f.WsRKXWASU.M.SCpM0NZuXpj2eQD2svJgipbrkn6AueOjy299WKq";
    public static final Role EXPECTED_USER_ROLE = Role.USER;

    public static final String TEST_FIRST_NAME = "Will";
    public static final String TEST_LAST_NAME = "Smith";
    public static final String TEST_EMAIL = "Will_Smith@gmail.com";
    public static final String TEST_PASSWORD = "password";
    public static final String TEST_QUESTION = "test question";
    public static final String TEST_ANSWER = "test correct answer";
    public static final String TEST_FIRST_FALSE_ANSWER = "test first false answer";
    public static final String TEST_SECOND_FALSE_ANSWER = "test second false answer";
    public static final String TEST_THIRD_FALSE_ANSWER = "test third false answer";
    public static final String TEST_FOURTH_FALSE_ANSWER = "test fourth false answer";
    public static final String TEST_FIFTH_FALSE_ANSWER = "test fifth false answer";
    public static final String UPDATE_TEST_QUESTION = "update test question";
    public static final String UPDATE_TEST_CORRECT_ANSWER = "update test correct answer";
    public static final String UPDATE_TEST_FIRST_FALSE_ANSWER = "update test first false answer";
    public static final String UPDATE_TEST_SECOND_FALSE_ANSWER = "update test second false answer";
    public static final String UPDATE_TEST_THIRD_FALSE_ANSWER = "update test third false answer";
    public static final String UPDATE_TEST_FOURTH_FALSE_ANSWER = "update test fourth false answer";
    public static final String UPDATE_TEST_FIFTH_FALSE_ANSWER = "update test fifth false answer";

    public static final String TEST_FILE_LOG = "src/test/resources/logs/log-file.log";
    public static final String TEST_STR_LOG = "2023-12-08 11:32:02 [main] INFO  com.zaxxer.hikari.HikariDataSource - postgres - Start completed.";

    public static final int ALL_USERS_SIZE = 22;
    public static final int ALL_STATISTIC_SIZE = 2;
    public static final int ALL_TESTING_INTERVIEW_SIZE = 53;
    public static final int ALL_QUESTION_INTERVIEW_SIZE = 432;
    public static final int SIZE_LIST_STUDY_QUESTION_INTERVIEW_EXPECTED_USER = 3;

    public static final int ID_TESTING_INTERVIEW = 1;
    public static final String TESTING_INTERVIEW_QUESTION = "Можем ли мы иметь два main-метода в классе в языке программирования Java?";
    public static final String TESTING_INTERVIEW_FIRST_FALSE_ANSWER = "Нет";
    public static final String TESTING_INTERVIEW_CORRECT_ANSWER = "Да";

    public static final int ID_QUESTION_INTERVIEW = 275;
    public static final String QUESTION_INTERVIEW_QUESTION = "Что такое TCL? Какие операции в него входят? Рассказать про них.";
    public static final String QUESTION_INTERVIEW_ANSWER = "Операторы управления транзакциями (Transaction Control Language, TCL): <br><span class=\"selected_text color_selected_text\">BEGIN</span> служит для определения начала транзакции. <br><span class=\"selected_text color_selected_text\">COMMIT</span> применяет транзакцию. <br><span class=\"selected_text color_selected_text\">ROLLBACK</span> откатывает все изменения, сделанные в контексте текущей транзакции. <br><span class=\"selected_text color_selected_text\">SAVEPOINT</span> разбивает транзакцию на более мелкие.";

    public static final int STUDY_QUESTION_ID_159 = 159;
    public static final int STUDY_QUESTION_ID_275 = 275;
    public static final int STUDY_QUESTION_ID_336 = 336;

    public static final int TEST_RESULT_STATISTIC = 100;

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
