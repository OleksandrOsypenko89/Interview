package com.osypenko.controller.template.admin;

import com.osypenko.controller.BaseMvcTests;
import com.osypenko.services.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.USER;
import static com.osypenko.constant.NameSessionAttributes.USER_UPDATE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UpdateOrDeleteUserControllerTest extends BaseMvcTests {
    @Autowired
    private UserService userService;

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void foundUser() throws Exception {
        perform(get(UPDATE_OR_DELETE_USER)
                .sessionAttr(USER, admin)
                .sessionAttr(USER_UPDATE, user)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_ADMIN + UPDATE_OR_DELETE_USER));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateOrDeletePageUserNotAdmin() throws Exception {
        perform(get(UPDATE_OR_DELETE_USER)
                .sessionAttr(USER, user)
                .sessionAttr(USER_UPDATE, user)
        )
                .andExpect(status().isForbidden());
    }

    @Test
    void updateOrDeletePageUserUnauthorized() throws Exception {
        perform(get(UPDATE_OR_DELETE_USER))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = ADMIN_MAIL)
    void deleteUser() throws Exception {
        perform(post(DELETE_USER)
                .sessionAttr(USER_UPDATE, user)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(ADMIN_PAGE))
                .andExpect(view().name(REDIRECT + ADMIN_PAGE));
        Assertions.assertEquals(ALL_USERS_FINAL_SIZE - 1, userService.getAll().size());
    }
}