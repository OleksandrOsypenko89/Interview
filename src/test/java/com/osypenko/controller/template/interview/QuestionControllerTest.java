package com.osypenko.controller.template.interview;

import com.osypenko.controller.BaseMvcTests;
import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.services.interview.QuestionService;
import com.osypenko.services.statistics.StatisticService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;

import java.util.List;

import static com.osypenko.constant.Constant.SIZE_QUESTION;
import static com.osypenko.constant.Endpoints.*;
import static com.osypenko.constant.NameSessionAttributes.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class QuestionControllerTest extends BaseMvcTests {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private StatisticService statisticService;

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getInterview() throws Exception {
        List<QuestionInterview> listInterview = questionService.listFilling(user);
        perform(get(QUESTION)
                .sessionAttr(USER, user)
                .sessionAttr(KNOW, 0)
                .sessionAttr(LIST_QUESTION, listInterview)
        )
                .andExpect(status().isOk())
                .andExpect(view().name(DIRECTORY_INTERVIEW + QUESTION));
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void getInterviewListIsEmpty() throws Exception {
        List<QuestionInterview> listInterview = List.of();
        perform(get(QUESTION)
                .sessionAttr(USER, user)
                .sessionAttr(KNOW, 0)
                .sessionAttr(LIST_QUESTION, listInterview)
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
    void knowAnswer() throws Exception {
        List<QuestionInterview> listInterview = questionService.listFilling(user);
        QuestionInterview question = listInterview.get(0);
        perform(post(KNOW_ANSWER)
                .sessionAttr(KNOW, 0)
                .sessionAttr(QUESTION_INTERVIEW, question)
                .sessionAttr(LIST_QUESTION, listInterview)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(QUESTION))
                .andExpect(view().name(REDIRECT + QUESTION));
        Assertions.assertEquals( SIZE_QUESTION - 1, listInterview.size());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void noAnswer() throws Exception {
        List<QuestionInterview> listInterview = questionService.listFilling(user);
        QuestionInterview question = listInterview.get(0);
        perform(post(NO_ANSWER)
                .sessionAttr(USER, user)
                .sessionAttr(QUESTION_INTERVIEW, question)
                .sessionAttr(LIST_QUESTION, listInterview)
        )
                .andExpect(status().isFound())
                .andExpect(redirectedUrl(QUESTION))
                .andExpect(view().name(REDIRECT + QUESTION));
        Assertions.assertEquals( SIZE_QUESTION - 1, listInterview.size());
        Assertions.assertEquals(SIZE_LIST_STUDY_QUESTION_INTERVIEW_EXPECTED_USER + 1, user.getListStudyQuestion().size());
    }
}