package com.osypenko.controller.template.statistic;

import com.osypenko.controller.BaseMvcTests;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class AllStatisticsControllerTest extends BaseMvcTests {

    @Test
    @WithUserDetails(value = USER_MAIL)
    void allStatistic() throws Exception {
        perform(get(ALL_STATISTICS)
                .sessionAttr(USER, user)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_STATISTIC + ALL_STATISTICS));
    }

    @Test
    void allStatisticPageUserUnauthorized() throws Exception {
        perform(get(ALL_STATISTICS))
                .andExpect(status().isUnauthorized());
    }
}