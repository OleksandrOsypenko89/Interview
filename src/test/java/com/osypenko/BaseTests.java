package com.osypenko;

import com.osypenko.dto.UserDTO;
import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.interview.question.Topic;
import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.statistic.Type;
import com.osypenko.model.users.Role;
import com.osypenko.model.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

import static com.osypenko.constant.Constant.SIZE_QUESTION;

@Transactional
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class BaseTests {
    public User newUser;
    public UserDTO userDTO;
    public User expectedUser;
    public Statistic statistic;
    public UserDetails userDetails;
    public QuestionInterview questionInterview;

    public static final long EXPECTED_USER_ID = 4L;
    public static final String EXPECTED_USER_EMAIL = "demo@gmail.com";
    public static final String EXPECTED_USER_PASSWORD = "{bcrypt}$2a$10$f.WsRKXWASU.M.SCpM0NZuXpj2eQD2svJgipbrkn6AueOjy299WKq";
    public static final Role EXPECTED_USER_ROLE = Role.USER;

    public static final String TEST_FIRST_NAME = "Will";
    public static final String TEST_LAST_NAME = "Smith";
    public static final String TEST_EMAIL = "Will_Smith@gmail.com";
    public static final String TEST_PASSWORD = "password";
    public static final String TEST_QUESTION = "test_question";
    public static final String TEST_ANSWER = "test_answer";

    public static final int ALL_USERS_SIZE = 22;
    public static final int ALL_STATISTIC_SIZE = 2;
    public static final int ALL_QUESTION_INTERVIEW_SIZE = 432;
    public static final int SIZE_LIST_STUDY_QUESTION_INTERVIEW_EXPECTED_USER = 3;

    public static final int ID_QUESTION_INTERVIEW = 275;
    public static final String QUESTION_INTERVIEW = "Что такое TCL? Какие операции в него входят? Рассказать про них.";
    public static final String ANSWER_INTERVIEW = "Операторы управления транзакциями (Transaction Control Language, TCL): <br><span class=\"selected_text color_selected_text\">BEGIN</span> служит для определения начала транзакции. <br><span class=\"selected_text color_selected_text\">COMMIT</span> применяет транзакцию. <br><span class=\"selected_text color_selected_text\">ROLLBACK</span> откатывает все изменения, сделанные в контексте текущей транзакции. <br><span class=\"selected_text color_selected_text\">SAVEPOINT</span> разбивает транзакцию на более мелкие.";

    public static final int TEST_RESULT_STATISTIC = 100;
    public static final Timestamp TEST_STATISTIC_DATE = new Timestamp(System.currentTimeMillis());

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
        statistic = Statistic.builder()
                .date(TEST_STATISTIC_DATE)
                .result(TEST_RESULT_STATISTIC)
                .userId(EXPECTED_USER_ID)
                .type(Type.QUESTIONS)
                .knowAnswer(SIZE_QUESTION)
                .build();
        questionInterview = QuestionInterview.builder()
                .question(TEST_QUESTION)
                .answer(TEST_ANSWER)
                .topic(Topic.CORE1)
                .build();
    }
}