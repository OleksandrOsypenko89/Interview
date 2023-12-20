package com.osypenko.controller.template.admin;

import com.osypenko.controller.BaseMvcTests;
import com.osypenko.services.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;

import static com.osypenko.TestConstants.*;
import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.USER;
import static com.osypenko.constant.NameSessionAttributes.USER_UPDATE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UpdateOrDeleteUserControllerTest extends BaseMvcTests {
    private final UserService userService;

    public UpdateOrDeleteUserControllerTest(UserService userService) {
        this.userService = userService;
    }

    @Test
    @WithUserDetails(value = EXPECTED_ADMIN_MAIL)
    void foundUser() throws Exception {
        perform(get(UPDATE_OR_DELETE_USER)
                .sessionAttr(USER, expectedAdmin)
                .sessionAttr(USER_UPDATE, expectedUser)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_ADMIN + UPDATE_OR_DELETE_USER));
    }

    @Test
    @WithUserDetails(value = EXPECTED_USER_EMAIL)
    void updateOrDeletePageUserNotAdmin() throws Exception {
        perform(get(UPDATE_OR_DELETE_USER)
                .sessionAttr(USER, expectedUser)
                .sessionAttr(USER_UPDATE, expectedUser)
        )
                .andExpect(status().isForbidden());
    }

    @Test
    void updateOrDeletePageUserUnauthorized() throws Exception {
        perform(get(UPDATE_OR_DELETE_USER))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = EXPECTED_ADMIN_MAIL)
    void deleteUser() throws Exception {
        perform(post(DELETE_USER)
                .sessionAttr(USER_UPDATE, expectedUser)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(ADMIN_PAGE))
                .andExpect(view().name(REDIRECT + ADMIN_PAGE));
        Assertions.assertEquals(ALL_USERS_SIZE - 1, userService.getAll().size());
    }
}