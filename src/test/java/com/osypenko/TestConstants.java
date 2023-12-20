package com.osypenko;

import com.osypenko.model.users.Role;

public class TestConstants {
    public static final long EXPECTED_USER_ID = 4L;
    public static final String EXPECTED_USER_EMAIL = "demo_test@gmail.com";
    public static final String EXPECTED_USER_PASSWORD = "{bcrypt}$2a$10$f.WsRKXWASU.M.SCpM0NZuXpj2eQD2svJgipbrkn6AueOjy299WKq";
    public static final Role EXPECTED_USER_ROLE = Role.USER;

    public static final String EXPECTED_ADMIN_MAIL = "Johnny_Depp@gmail.com";

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

    public static final String LOG_DIRECTORY = "src/test/resources/logs";
    public static final String LOG_FILE_ADDRESS = LOG_DIRECTORY + "/log-file.log";
    public static final String TEST_STR_LOG = "2023-12-08 11:32:02 [main] INFO  com.zaxxer.hikari.HikariDataSource - postgres - Start completed.";

    public static final String PASSWORD_ONE = "passwordOne";
    public static final String PASSWORD_TWO = "passwordTwo";
    public static final String BUTTON_ANSWER = "YES";

    public static final String TEST_TEXT = "test text feedback";

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

    public static final int REGISTRATION_CODE = 123456;
    public static final int WRONG_REGISTRATION_CODE = 654321;
}
