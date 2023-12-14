package com.osypenko.controller.template.recoverypassword;

import com.osypenko.controller.BaseMvcTests;
import org.junit.jupiter.api.Test;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.EMAIL;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class NewPasswordControllerTest extends BaseMvcTests {

    @Test
    void newPassword() throws Exception {
        perform(get(NEW_PASSWORD))
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_PASSWORD_RECOVERY + NEW_PASSWORD));
    }

    @Test
    void redirectLoginCorrect() throws Exception {
        perform(post(SAVE_NEW_PASSWORD)
                .sessionAttr(EMAIL, USER_MAIL)
                .param("passwordOne", PASSWORD_ONE)
                .param("passwordTwo", PASSWORD_ONE)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(LOGIN))
                .andExpect(view().name(REDIRECT + LOGIN));
    }

    @Test
    void redirectLoginWrong() throws Exception {
        perform(post(SAVE_NEW_PASSWORD)
                .sessionAttr(EMAIL, USER_MAIL)
                .param("passwordOne", PASSWORD_ONE)
                .param("passwordTwo", PASSWORD_TWO)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(NEW_PASSWORD))
                .andExpect(view().name(REDIRECT + NEW_PASSWORD));
    }
}