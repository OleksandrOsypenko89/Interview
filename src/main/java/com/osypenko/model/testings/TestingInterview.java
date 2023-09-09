package com.osypenko.model.testings;

import com.osypenko.model.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "testing_interview")
@ToString
public class TestingInterview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String question;

    private String firstIncorrectAnswer;

    private String secondIncorrectAnswer;

    private String thirdIncorrectAnswer;

    private String correctAnswer;

    private String answer;

    @ToString.Exclude
    @SuppressWarnings("com.haulmont.jpb.ManyToManyCascadeRemove")
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "lists_question_and_testing_interviews",
            joinColumns = @JoinColumn(name = "testing_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> userListQuestion;
}
