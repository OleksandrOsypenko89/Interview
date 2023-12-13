package com.osypenko.controller.template.statistic;

import com.osypenko.controller.BaseMvcTests;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.NEW_STATISTICS_ADDED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class StatisticControllerTests extends BaseMvcTests {

    @Test
    @WithUserDetails(value = USER_MAIL)
    void statistic() throws Exception {
        perform(get(STATISTIC)
                .sessionAttr(NEW_STATISTICS_ADDED, newStatistic)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_STATISTIC + STATISTIC));
    }
}