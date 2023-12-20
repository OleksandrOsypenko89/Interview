package com.osypenko.controller.template.userpage;

import com.osypenko.controller.BaseMvcTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;

import static com.osypenko.TestConstants.*;
import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserPageControllerTests extends BaseMvcTests {

    @Test
    @WithUserDetails(value = EXPECTED_USER_EMAIL)
    void userPageAuthorized() throws Exception {
        perform(get(USER_PAGE))
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_USER_PAGES + USER_PAGE));
    }

    @Test
    void userPageUnauthorized() throws Exception {
        perform(get(USER_PAGE))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = EXPECTED_USER_EMAIL)
    void questionPage() throws Exception {
        perform(get(QUESTION_PAGE)
                .sessionAttr(USER, expectedUser)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(QUESTION))
                .andExpect(view().name(REDIRECT + QUESTION));
    }

    @Test
    @WithUserDetails(value = EXPECTED_USER_EMAIL)
    void testingPage() throws Exception {
        perform(get(TESTING_PAGE)
                .sessionAttr(USER, expectedUser)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(TESTING))
                .andExpect(view().name(REDIRECT + TESTING));
    }

    @Test
    @WithUserDetails(value = EXPECTED_USER_EMAIL)
    void allStatisticPage() throws Exception {
        perform(get(ALL_STATISTICS_PAGE))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(ALL_STATISTICS))
                .andExpect(view().name(REDIRECT + ALL_STATISTICS));
    }

    @Test
    void logout() throws Exception {
        perform(get(LOGOUT))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails(value = EXPECTED_ADMIN_MAIL)
    void adminPage() throws Exception {
        perform(post(ADMIN_PAGE)
                .sessionAttr(USER, expectedAdmin)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(ADMIN_PAGE))
                .andExpect(view().name(REDIRECT + ADMIN_PAGE));
    }

    @Test
    @WithUserDetails(value = EXPECTED_USER_EMAIL)
    void deleteStudyQuestion() throws Exception{
        perform(post(DELETE_STUDY_QUESTION)
                .sessionAttr(USER, expectedUser)
                .param("idQuestion", String.valueOf(STUDY_QUESTION_ID_159))
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(USER_PAGE))
                .andExpect(view().name(REDIRECT + USER_PAGE));
        Assertions.assertEquals(SIZE_LIST_STUDY_QUESTION_INTERVIEW_EXPECTED_USER - 1, expectedUser.getListStudyQuestion().size());
    }
}