package com.osypenko.controller.template.userpage;

import com.osypenko.controller.BaseMvcTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserPageControllerTests extends BaseMvcTests {

    @Test
    @WithUserDetails(value = USER_MAIL)
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
    @WithUserDetails(value = USER_MAIL)
    void questionPage() throws Exception {
        perform(get(QUESTION_PAGE)
                .sessionAttr(USER, user)
        )
                .andExpect(status().isFound())
                .andExpect(view().name(REDIRECT + QUESTION));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void testingPage() throws Exception {
        perform(get(TESTING_PAGE)
                .sessionAttr(USER, user)
        )
                .andExpect(status().isFound())
                .andExpect(view().name(REDIRECT + TESTING));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void allStatisticPage() throws Exception {
        perform(get(ALL_STATISTICS_PAGE))
                .andExpect(status().isFound())
                .andExpect(view().name(REDIRECT + ALL_STATISTICS));
    }

    @Test
    void logout() throws Exception {
        perform(get(LOGOUT))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void adminPage() throws Exception {
        perform(post(ADMIN_PAGE)
                .sessionAttr(USER, admin)
        )
                .andExpect(status().isFound())
                .andExpect(view().name(REDIRECT + ADMIN_PAGE));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void deleteStudyQuestion() throws Exception{
        perform(post(DELETE_STUDY_QUESTION)
                .sessionAttr(USER, user)
                .param("idQuestion", String.valueOf(STUDY_QUESTION_ID_159))
        )
                .andExpect(status().isFound())
                .andExpect(view().name(REDIRECT + USER_PAGE));
        Assertions.assertEquals(SIZE_LIST_STUDY_QUESTION_INTERVIEW_USER - 1, user.getListStudyQuestion().size());
    }
}