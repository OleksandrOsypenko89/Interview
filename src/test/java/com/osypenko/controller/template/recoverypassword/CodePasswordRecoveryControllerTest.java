package com.osypenko.controller.template.recoverypassword;

import com.osypenko.controller.BaseMvcTests;
import org.junit.jupiter.api.Test;

import static com.osypenko.TestConstants.REGISTRATION_CODE;
import static com.osypenko.TestConstants.WRONG_REGISTRATION_CODE;
import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.CODE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CodePasswordRecoveryControllerTest extends BaseMvcTests {

    @Test
    void code() throws Exception {
        perform(get(CODE_PASSWORD_RECOVERY))
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_PASSWORD_RECOVERY + CODE_PASSWORD_RECOVERY));
    }

    @Test
    void newPasswordCorrect() throws Exception {
        perform(post(NEW_PASSWORD)
                .sessionAttr(CODE, REGISTRATION_CODE)
                .param("codeUser", String.valueOf(REGISTRATION_CODE))
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(NEW_PASSWORD))
                .andExpect(view().name(REDIRECT + NEW_PASSWORD));
    }

    @Test
    void newPasswordWrong() throws Exception {
        perform(post(NEW_PASSWORD)
                .sessionAttr(CODE, REGISTRATION_CODE)
                .param("codeUser", String.valueOf(WRONG_REGISTRATION_CODE))
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(CODE_PASSWORD_RECOVERY))
                .andExpect(view().name(REDIRECT + CODE_PASSWORD_RECOVERY));
    }
}