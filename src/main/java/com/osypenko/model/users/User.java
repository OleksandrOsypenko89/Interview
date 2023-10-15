package com.osypenko.model.users;

import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.interview.testings.TestingInterview;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name= "last_name")
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    private Timestamp registrationDate = new Timestamp(System.currentTimeMillis());

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "lists_question_interview",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
    private Set<QuestionInterview> listQuestionInterviews;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "lists_testing_interview",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "testing_id", referencedColumnName = "id"))
    private Set<TestingInterview> listQuestionTesting;

    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "list_study_questions",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
    private Set<QuestionInterview> listStudyQuestion;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private Set<Statistic> statistic;
}
