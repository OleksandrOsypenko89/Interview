package com.osypenko.controller.template.registration;

import com.osypenko.controller.BaseMvcTests;
import com.osypenko.model.users.User;
import com.osypenko.services.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.CODE_REGISTRATION;
import static com.osypenko.constant.NameSessionAttributes.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CodeForRegistrationControllerTest extends BaseMvcTests {
    @Autowired
    private UserService userService;

    @Test
    void codeUser() throws Exception {
        perform(get(CODE_FOR_REGISTRATION))
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_REGISTRATION + CODE_FOR_REGISTRATION));
    }

    @Test
    void createNewUserCorrect() throws Exception {
        User newUser = userService.createNewUser(new User(), TEST_FIRST_NAME, TEST_LAST_NAME, TEST_EMAIL, TEST_PASSWORD);
        perform(post(NEW_USER)
                .sessionAttr(USER, newUser)
                .sessionAttr(CODE_REGISTRATION, REGISTRATION_CODE)
                .param("codeUser", String.valueOf(REGISTRATION_CODE))
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(LOGIN))
                .andExpect(view().name(REDIRECT + LOGIN));
        Assertions.assertEquals(ALL_USERS_FINAL_SIZE + 1, userService.getAll().size());
    }

    @Test
    void createNewUserWrong() throws Exception {
        User newUser = userService.createNewUser(new User(), TEST_FIRST_NAME, TEST_LAST_NAME, TEST_EMAIL, TEST_PASSWORD);
        perform(post(NEW_USER)
                .sessionAttr(USER, newUser)
                .sessionAttr(CODE_REGISTRATION, REGISTRATION_CODE)
                .param("codeUser", String.valueOf(WRONG_REGISTRATION_CODE))
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(CODE_FOR_REGISTRATION))
                .andExpect(view().name(REDIRECT + CODE_FOR_REGISTRATION));
        Assertions.assertEquals(ALL_USERS_FINAL_SIZE, userService.getAll().size());
    }
}