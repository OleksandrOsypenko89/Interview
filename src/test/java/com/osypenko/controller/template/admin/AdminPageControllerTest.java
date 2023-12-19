package com.osypenko.controller.template.admin;

import com.osypenko.controller.BaseMvcTests;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AdminPageControllerTest extends BaseMvcTests {

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void getAdminPage() throws Exception {
        perform(get(ADMIN_PAGE)
                .sessionAttr(USER, admin)
                .sessionAttr(FILES_IN_DIRECTORY_LOGS, LOG_DIRECTORY)
                .sessionAttr(SIZE_ALL_USERS, ALL_USERS_FINAL_SIZE)
                .sessionAttr(SIZE_ALL_QUESTION_INTERVIEW, ALL_QUESTIONS_FINAL_SIZE)
                .sessionAttr(SIZE_ALL_TESTING_INTERVIEW, ALL_TESTING_FINAL_SIZE)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_ADMIN + ADMIN_PAGE));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getAdminPageNoAdmin() throws Exception {
        perform(get(ADMIN_PAGE))
                .andExpect(status().isForbidden());
    }

    @Test
    void getAdminPageUserUnauthorized() throws Exception {
        perform(get(ADMIN_PAGE))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void adminQuestionInterviewIsFoundNameQuestion() throws Exception {
        perform(post(ADMIN_SEARCH_QUESTION)
                .param("interview", "Что такое ООП?")
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(CREATE_AND_UPDATE_QUESTION))
                .andExpect(view().name(REDIRECT + CREATE_AND_UPDATE_QUESTION));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void adminQuestionInterviewIsFoundIdQuestion() throws Exception {
        perform(post(ADMIN_SEARCH_QUESTION)
                .param("interview", "1")
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(CREATE_AND_UPDATE_QUESTION))
                .andExpect(view().name(REDIRECT + CREATE_AND_UPDATE_QUESTION));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void adminQuestionInterviewNotFound() throws Exception {
        perform(post(ADMIN_SEARCH_QUESTION)
                .param("interview", "Что такое")
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(ADMIN_PAGE))
                .andExpect(view().name(REDIRECT + ADMIN_PAGE));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void adminNewQuestionInterview() throws Exception {
        perform(post(ADMIN_NEW_QUESTION))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(CREATE_AND_UPDATE_QUESTION))
                .andExpect(view().name(REDIRECT + CREATE_AND_UPDATE_QUESTION));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void adminQuestionTestingIsFound() throws Exception {
        perform(post(ADMIN_SEARCH_TESTING)
                .param("id", "1")
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(CREATE_AND_UPDATE_TESTING))
                .andExpect(view().name(REDIRECT + CREATE_AND_UPDATE_TESTING));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void adminQuestionTestingNotFound() throws Exception {
        perform(post(ADMIN_SEARCH_TESTING)
                .param("id", "88")
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(ADMIN_PAGE))
                .andExpect(view().name(REDIRECT + ADMIN_PAGE));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void adminNewQuestionTesting() throws Exception {
        perform(post(ADMIN_NEW_TESTING))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(CREATE_AND_UPDATE_TESTING))
                .andExpect(view().name(REDIRECT + CREATE_AND_UPDATE_TESTING));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void adminSearchUserIsFound() throws Exception {
        perform(post(ADMIN_SEARCH_USER)
                .param("email", USER_MAIL)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(UPDATE_OR_DELETE_USER))
                .andExpect(view().name(REDIRECT + UPDATE_OR_DELETE_USER));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void adminSearchUserNotFound() throws Exception {
        perform(post(ADMIN_SEARCH_USER)
                .param("email", TEST_EMAIL)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(ADMIN_PAGE))
                .andExpect(view().name(REDIRECT + ADMIN_PAGE));
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void redirectAdminPage() throws Exception {
        perform(post(REDIRECT_ADMIN_PAGE))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(ADMIN_PAGE))
                .andExpect(view().name(REDIRECT + ADMIN_PAGE));
    }
}