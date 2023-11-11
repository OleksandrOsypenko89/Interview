package com.osypenko.controllers.api.userpage;

import com.osypenko.dto.QuestionInterviewDTO;
import com.osypenko.dto.StatisticDTO;
import com.osypenko.dto.TestingInterviewDTO;
import com.osypenko.dto.UserDTO;
import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.osypenko.services.user.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.osypenko.constant.NameMapping.*;

@RestController
@RequiredArgsConstructor
public class UserPageRestController {
    private final UserDetailsService userDetailsService;

    @GetMapping(USER_API)
    public ResponseEntity<UserDTO> getUserInApi(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userDetailsService.getUser(userDetails);
        Set<QuestionInterviewDTO> listQuestionInterviewDTO = new HashSet<>();
        Set<TestingInterviewDTO> listTestingInterviewDTO = new HashSet<>();
        Set<QuestionInterviewDTO> listStudyQuestionDTO = new HashSet<>();
        Set<StatisticDTO> listStatisticDTO = new HashSet<>();

        Set<QuestionInterview> listQuestionInterviews = user.getListQuestionInterviews();
        for (QuestionInterview question : listQuestionInterviews) {
            QuestionInterviewDTO questionInterviewDTO = new QuestionInterviewDTO(
                    question.getId()
                    , question.getAnswer()
                    , question.getQuestion()
                    , question.getTopic()
            );
            listQuestionInterviewDTO.add(questionInterviewDTO);
        }

        Set<TestingInterview> listQuestionTesting = user.getListQuestionTesting();
        for (TestingInterview testing : listQuestionTesting) {
            TestingInterviewDTO testingInterviewDTO = new TestingInterviewDTO(
                    testing.getId()
                    , testing.getQuestion()
                    , testing.getPicture()
                    , testing.getFirstFalseAnswer()
                    , testing.getSecondFalseAnswer()
                    , testing.getThirdFalseAnswer()
                    , testing.getFourthFalseAnswer()
                    , testing.getFifthFalseAnswer()
                    , testing.getCorrectAnswer()
                    , testing.getAnswer()
            );
            listTestingInterviewDTO.add(testingInterviewDTO);
        }

        Set<QuestionInterview> listStudyQuestion = user.getListStudyQuestion();
        for (QuestionInterview study : listStudyQuestion) {
            QuestionInterviewDTO questionInterviewDTO = new QuestionInterviewDTO(
                    study.getId()
                    , study.getAnswer()
                    , study.getQuestion()
                    , study.getTopic()
            );
            listStudyQuestionDTO.add(questionInterviewDTO);
        }

        Set<Statistic> statistic = user.getStatistic();
        for (Statistic stat : statistic) {
            StatisticDTO statisticDTO = new StatisticDTO(
                    stat.getId()
                    , stat.getDate()
                    , stat.getResult()
                    , stat.getType()
                    , stat.getKnowAnswer()
                    , stat.getQuestions()
            );
            listStatisticDTO.add(statisticDTO);
        }

        UserDTO userDTO = new UserDTO(
                user.getId()
                , user.getFirstName()
                , user.getLastName()
                , user.getEmail()
                , user.getRole()
                , user.getRegistrationDate()
                , listQuestionInterviewDTO
                , listTestingInterviewDTO
                , listStudyQuestionDTO
                , listStatisticDTO
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDTO);
    }
}
