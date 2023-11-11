package com.osypenko.dto;

import com.osypenko.model.interview.question.Topic;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class QuestionInterviewDTO {
    private Integer id;
    private String question;
    private String answer;
    private Topic topic;
}