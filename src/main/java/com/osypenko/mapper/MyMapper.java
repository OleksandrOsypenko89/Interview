package com.osypenko.mapper;

import com.osypenko.dto.*;
import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.users.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MyMapper {

    private Set<QuestionInterviewDTO> getQuestionInterviewDTOSet(User user) {
        Set<QuestionInterviewDTO> listQuestionInterviewDTO = new HashSet<>();
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
        return listQuestionInterviewDTO;
    }

    private Set<TestingInterviewDTO> getTestingInterviewDTOSet(User user) {
        Set<TestingInterviewDTO> listTestingInterviewDTO = new HashSet<>();
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
        return listTestingInterviewDTO;
    }

    private Set<QuestionInterviewDTO> getStudyQuestionInterviewDTOSet(User user) {
        Set<QuestionInterviewDTO> listStudyQuestionDTO = new HashSet<>();
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
        return listStudyQuestionDTO;
    }

    private Set<StatisticDTO> getStatisticDTOSet(User user) {
        Set<StatisticDTO> listStatisticDTO = new HashSet<>();
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
        return listStatisticDTO;
    }

    public UserDTO updateUserInUserDTO(User user) {
        return new UserDTO(
                user.getId()
                , user.getFirstName()
                , user.getLastName()
                , user.getEmail()
                , user.getRole()
                , user.getRegistrationDate()
                , getQuestionInterviewDTOSet(user)
                , getTestingInterviewDTOSet(user)
                , getStudyQuestionInterviewDTOSet(user)
                , getStatisticDTOSet(user)
        );
    }

    public UserDTO updateUser(User user, UpdateUserDTO updateUserDTO) {
        UserDTO userDTO = updateUserInUserDTO(user);
        userDTO.setFirstName(updateUserDTO.getFirstName());
        userDTO.setLastName(updateUserDTO.getLastName());
        userDTO.setEmail(updateUserDTO.getEmail());
        return userDTO;
    }
}
