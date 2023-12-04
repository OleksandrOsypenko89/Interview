package com.osypenko;

import com.osypenko.dto.UserDTO;
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
    public User expectedUser;
    public UserDetails userDetails;
    public UserDTO userDTO;
    public static final long EXPECTED_USER_ID = 4L;
    public static final String EXPECTED_USER_EMAIL = "demo@gmail.com";
    public static final String EXPECTED_USER_PASSWORD = "{bcrypt}$2a$10$f.WsRKXWASU.M.SCpM0NZuXpj2eQD2svJgipbrkn6AueOjy299WKq";
    public static final Role EXPECTED_USER_ROLE = Role.USER;

    public static final String TEST_FIRST_NAME = "Will";
    public static final String TEST_LAST_NAME = "Smith";
    public static final String TEST_EMAIL = "Will_Smith@gmail.com";
    public static final String TEST_PASSWORD = "password";

    public static final int ALL_USERS_SIZE = 22;

    public static final int ID_QUESTION_INTERVIEW = 275;
    public static final int SIZE_LIST_STUDY_QUESTION_INTERVIEW_EXPECTED_USER = 3;

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
    }
}
