package com.osypenko.controller.template.admin;

import com.osypenko.controller.BaseMvcTests;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;

import static com.osypenko.TestConstants.EXPECTED_USER_EMAIL;
import static com.osypenko.TestConstants.TEST_TEXT;
import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FeedbackControllerTest extends BaseMvcTests {

    @Test
    @WithUserDetails(value = EXPECTED_USER_EMAIL)
    void feedback() throws Exception {
        perform(get(FEEDBACK)
                .sessionAttr(USER, expectedUser)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_ADMIN + FEEDBACK));
    }

    @Test
    void feedbackUserUnauthorized() throws Exception {
        perform(get(FEEDBACK))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = EXPECTED_USER_EMAIL)
    void textMe() throws Exception {
        perform(post(TEXT_ME)
                .sessionAttr(USER, expectedUser)
                .param("text", TEST_TEXT)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(USER_PAGE))
                .andExpect(view().name(REDIRECT + USER_PAGE));
    }
}