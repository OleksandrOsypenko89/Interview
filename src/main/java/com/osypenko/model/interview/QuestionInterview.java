package com.osypenko.model.interview;

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
@Table(name = "question_interview")
@ToString
public class QuestionInterview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String question;

    private String answer;

    private Integer urlId;

    private Integer imageId;

    @Enumerated(EnumType.STRING)
    private Topic topic;

    @SuppressWarnings("com.haulmont.jpb.ManyToManyCascadeRemove")
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "list_question_interviews",
            joinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> userListQuestion;

    @SuppressWarnings("com.haulmont.jpb.ManyToManyCascadeRemove")
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "list_study_questions",
            joinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> userStudyQuestion;
}
