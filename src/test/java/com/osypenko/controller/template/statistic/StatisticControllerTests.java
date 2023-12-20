package com.osypenko.controller.template.statistic;

import com.osypenko.controller.BaseMvcTests;
import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.statistic.Type;
import com.osypenko.services.statistics.StatisticService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithUserDetails;

import static com.osypenko.TestConstants.ALL_STATISTIC_SIZE;
import static com.osypenko.TestConstants.EXPECTED_USER_EMAIL;
import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.NEW_STATISTICS_ADDED;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class StatisticControllerTests extends BaseMvcTests {
    private final StatisticService statisticService;

    public StatisticControllerTests(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @Test
    @WithUserDetails(value = EXPECTED_USER_EMAIL)
    void statistic() throws Exception {
        Statistic newStatistic = statisticService.createNewStatistic(expectedUser, 100, 15, Type.QUESTIONS);
        perform(get(STATISTIC)
                .sessionAttr(NEW_STATISTICS_ADDED, newStatistic)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_STATISTIC + STATISTIC));
        Assertions.assertEquals(ALL_STATISTIC_SIZE + 1, statisticService.allStatistics().size());
    }

    @Test
    void statisticPageUserUnauthorized() throws Exception {
        perform(get(STATISTIC))
                .andExpect(status().isUnauthorized());
    }
}