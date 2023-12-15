package com.osypenko.controller.template.interview;

import com.osypenko.controller.BaseMvcTests;
import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.services.interview.TestingService;
import com.osypenko.services.statistics.StatisticService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;

import java.util.List;

import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.Endpoints.STATISTIC;
import static com.osypenko.constant.NameSessionAttributes.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TestingControllerTest extends BaseMvcTests {
    @Autowired
    private TestingService testingService;
    @Autowired
    private StatisticService statisticService;


    @Test
    @WithUserDetails(value = USER_MAIL)
    void getTesting() throws Exception {
        List<TestingInterview> listTesting = testingService.listFilling(user);
        perform(get(TESTING)
                .sessionAttr(USER, user)
                .sessionAttr(KNOW, 0)
                .sessionAttr(LIST_TESTING, listTesting)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_INTERVIEW + TESTING));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getTestingListIsEmpty() throws Exception {
        List<TestingInterview> listTesting = List.of();
        perform(get(TESTING)
                .sessionAttr(USER, user)
                .sessionAttr(KNOW, 0)
                .sessionAttr(LIST_TESTING, listTesting)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(STATISTIC))
                .andExpect(view().name(REDIRECT + STATISTIC));
        Assertions.assertEquals(ALL_STATISTIC_SIZE + 1, statisticService.allStatistics().size());
    }

    @Test
    void testingPageUserUnauthorized() throws Exception {
        perform(get(TESTING))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void answerTesting() throws Exception {
        List<TestingInterview> listTesting = testingService.listFilling(user);
        TestingInterview testingInterview = listTesting.get(0);
        perform(post(ANSWER_TESTING)
                .sessionAttr(KNOW, 0)
                .sessionAttr(QUESTION_TESTING, testingInterview)
                .sessionAttr(LIST_TESTING, listTesting)
                .param("buttonAnswer", BUTTON_ANSWER)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(TESTING))
                .andExpect(view().name(REDIRECT + TESTING));
    }
}