package com.osypenko.controller.template.admin;

import com.osypenko.controller.BaseMvcTests;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;

import static com.osypenko.TestConstants.*;
import static com.osypenko.constant.Endpoints.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class LogControllerTest extends BaseMvcTests {

    @Test
    @WithUserDetails(value = EXPECTED_ADMIN_MAIL)
    void filesLog() throws Exception {
        perform(get(LOG)
                .param("nameFile", LOG_FILE_ADDRESS)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_ADMIN + LOG));
    }

    @Test
    @WithUserDetails(value = EXPECTED_USER_EMAIL)
    void filesLogUserNotAdmin() throws Exception {
        perform(get(LOG)
                .param("nameFile", LOG_FILE_ADDRESS)
        )
                .andExpect(status().isForbidden());
    }

    @Test
    void filesLogUserUnauthorized() throws Exception {
        perform(get(UPDATE_OR_DELETE_USER))
                .andExpect(status().isUnauthorized());
    }
}