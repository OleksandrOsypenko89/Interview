package com.osypenko.controller.template.admin;

import com.osypenko.controller.BaseMvcTests;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.UPDATE_TESTING_INTERVIEW;
import static com.osypenko.constant.NameSessionAttributes.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CreateAndUpdateTestingControllerTest extends BaseMvcTests {

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createAndUpdateQuestion() throws Exception {
        perform(get(CREATE_AND_UPDATE_TESTING)
                .sessionAttr(USER, admin)
                .sessionAttr(UPDATE_TESTING_INTERVIEW, testingInterview)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_ADMIN + CREATE_AND_UPDATE_TESTING));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createAndUpdateQuestionNoAdmin() throws Exception {
        perform(get(CREATE_AND_UPDATE_TESTING)
                .sessionAttr(USER, admin)
                .sessionAttr(UPDATE_TESTING_INTERVIEW, testingInterview)
        )
                .andExpect(status().isForbidden());
    }

    @Test
    void createAndUpdateQuestionUserUnauthorized() throws Exception {
        perform(get(CREATE_AND_UPDATE_TESTING)
                .sessionAttr(USER, admin)
                .sessionAttr(UPDATE_TESTING_INTERVIEW, testingInterview)
        )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateTestingInterview() throws Exception {
        perform(post(VIEW_CHANGES_TESTING)
                .sessionAttr(USER, admin)
                .sessionAttr(UPDATE_TESTING_INTERVIEW, testingInterview)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(CREATE_AND_UPDATE_TESTING))
                .andExpect(view().name(REDIRECT + CREATE_AND_UPDATE_TESTING));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void saveTestingInterviewUpdate() throws Exception {
        perform(post(SAVE_TESTING_INTERVIEW)
                .sessionAttr(USER, admin)
                .sessionAttr(UPDATE_TESTING_INTERVIEW, testingInterview)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(ADMIN_PAGE))
                .andExpect(view().name(REDIRECT + ADMIN_PAGE));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void saveTestingInterviewCreate() throws Exception {
        perform(post(SAVE_TESTING_INTERVIEW)
                .sessionAttr(USER, admin)
                .sessionAttr(UPDATE_TESTING_INTERVIEW, testingInterview)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(ADMIN_PAGE))
                .andExpect(view().name(REDIRECT + ADMIN_PAGE));
    }
}