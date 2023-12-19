package com.osypenko.controller.template.admin;

import com.osypenko.controller.BaseMvcTests;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CreateAndUpdateQuestionControllerTest extends BaseMvcTests {

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void createAndUpdateQuestion() throws Exception {
        perform(get(CREATE_AND_UPDATE_QUESTION)
                .sessionAttr(USER, admin)
                .sessionAttr(UPDATE_QUESTION_INTERVIEW, questionInterview)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_ADMIN + CREATE_AND_UPDATE_QUESTION));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void createAndUpdateQuestionNoAdmin() throws Exception {
        perform(get(CREATE_AND_UPDATE_QUESTION)
                .sessionAttr(USER, admin)
                .sessionAttr(UPDATE_QUESTION_INTERVIEW, questionInterview)
        )
                .andExpect(status().isForbidden());
    }

    @Test
    void createAndUpdateQuestionUserUnauthorized() throws Exception {
        perform(get(CREATE_AND_UPDATE_QUESTION)
                .sessionAttr(USER, admin)
                .sessionAttr(UPDATE_QUESTION_INTERVIEW, questionInterview)
        )
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void updateQuestionInterview() throws Exception {
        perform(post(VIEW_CHANGES_QUESTION)
                .sessionAttr(USER, admin)
                .sessionAttr(UPDATE_QUESTION_INTERVIEW, questionInterview)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(CREATE_AND_UPDATE_QUESTION))
                .andExpect(view().name(REDIRECT + CREATE_AND_UPDATE_QUESTION));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void saveQuestionInterviewUpdate() throws Exception {
        perform(post(SAVE_QUESTION_INTERVIEW)
                .sessionAttr(USER, admin)
                .sessionAttr(UPDATE_QUESTION_INTERVIEW, questionInterview)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(ADMIN_PAGE))
                .andExpect(view().name(REDIRECT + ADMIN_PAGE));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void saveQuestionInterviewCreate() throws Exception {
        perform(post(SAVE_QUESTION_INTERVIEW)
                .sessionAttr(USER, admin)
                .sessionAttr(UPDATE_QUESTION_INTERVIEW, questionInterview)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(ADMIN_PAGE))
                .andExpect(view().name(REDIRECT + ADMIN_PAGE));
    }
}