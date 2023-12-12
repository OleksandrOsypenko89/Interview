package com.osypenko.services.user;

import com.osypenko.services.BaseTests;
import com.osypenko.constant.Constant;
import com.osypenko.dto.UserDTO;
import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.services.interview.QuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class UserDTOServiceTest extends BaseTests {
    private final UserDTOService userDTOService;
    private final QuestionService questionService;

    public UserDTOServiceTest(UserDTOService userDTOService, QuestionService questionService) {
        this.userDTOService = userDTOService;
        this.questionService = questionService;
    }

    @Test
    void saveRegistrationData() {
        userDTOService.saveRegistrationData(userDTO, newUser);
        assertEquals(TEST_FIRST_NAME, newUser.getFirstName());
        assertEquals(TEST_LAST_NAME, newUser.getLastName());
        assertEquals(TEST_EMAIL, newUser.getEmail());
        assertNotEquals(TEST_PASSWORD, newUser.getPassword());
    }

    @Test
    void updateDate() {
        UserDTO user = userDTOService.updateDate(userDetails, userDTO);
        Assertions.assertEquals(TEST_FIRST_NAME, user.getFirstName());
        Assertions.assertEquals(TEST_LAST_NAME, user.getLastName());
        Assertions.assertEquals(TEST_EMAIL, user.getEmail());
    }

    @Test
    void getUserDTO() {
        UserDTO user = userDTOService.getUserDTO(userDetails);
        Assertions.assertFalse(user.getListQuestionInterviews().isEmpty());
        Assertions.assertFalse(user.getListQuestionTesting().isEmpty());
        Assertions.assertEquals(Constant.SIZE_QUESTION, user.getListQuestionInterviews().size());
        Assertions.assertEquals(Constant.SIZE_QUESTION, user.getListQuestionTesting().size());
    }

    @Test
    void deleteStudyQuestionUser() {
        QuestionInterview questionInterview = questionService.get(ID_QUESTION_INTERVIEW).orElseThrow();
        UserDTO user = userDTOService.deleteStudyQuestionUser(userDetails, questionInterview);
        Assertions.assertEquals(SIZE_LIST_STUDY_QUESTION_INTERVIEW_EXPECTED_USER - 1, user.getListStudyQuestion().size());
    }
}