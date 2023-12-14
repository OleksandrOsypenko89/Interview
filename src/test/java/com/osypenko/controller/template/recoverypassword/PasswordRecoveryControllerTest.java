package com.osypenko.controller.template.recoverypassword;

import com.osypenko.controller.BaseMvcTests;
import org.junit.jupiter.api.Test;

import static com.osypenko.constant.Endpoints.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PasswordRecoveryControllerTest extends BaseMvcTests {

    @Test
    void forgotPassword() throws Exception {
        perform(get(PASSWORD_RECOVERY))
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_PASSWORD_RECOVERY + PASSWORD_RECOVERY));
    }

    @Test
    void newPasswordCorrect() throws Exception {
        perform(post(CONFIRMATION_CODE)
                .param("email", USER_MAIL)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(CODE_PASSWORD_RECOVERY))
                .andExpect(view().name(REDIRECT + CODE_PASSWORD_RECOVERY));
    }

    @Test
    void newPasswordWrong() throws Exception {
        perform(post(CONFIRMATION_CODE)
                .param("email", TEST_EMAIL)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(PASSWORD_RECOVERY))
                .andExpect(view().name(REDIRECT + PASSWORD_RECOVERY));
    }
}