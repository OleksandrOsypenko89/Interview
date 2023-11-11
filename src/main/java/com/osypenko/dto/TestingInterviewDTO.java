package com.osypenko.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TestingInterviewDTO {
    private Integer id;
    private String question;
    private String picture;
    private String firstFalseAnswer;
    private String secondFalseAnswer;
    private String thirdFalseAnswer;
    private String fourthFalseAnswer;
    private String fifthFalseAnswer;
    private String correctAnswer;
    private String answer;
}