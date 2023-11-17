package com.osypenko.dto;

import com.osypenko.model.users.Role;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private Timestamp registrationDate;
    private Set<QuestionInterviewDTO> listQuestionInterviews;
    private Set<TestingInterviewDTO> listQuestionTesting;
    private Set<QuestionInterviewDTO> listStudyQuestion;
    private Set<StatisticDTO> statistic;
}
