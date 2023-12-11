package com.osypenko.services.interview;

import com.osypenko.model.interview.question.QuestionInterview;
import com.osypenko.model.users.User;
import com.osypenko.repository.QuestionInterviewRepository;
import com.osypenko.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionService extends Interview {
    private final QuestionInterviewRepository questionInterviewRepository;
    private final UserService userService;

    public List<QuestionInterview> getAll() {
        return questionInterviewRepository.findAll();
    }

    public Optional<QuestionInterview> get(Integer id) {
        return questionInterviewRepository.findById(id);
    }

    public QuestionInterview save(QuestionInterview questionInterview) {
        questionInterviewRepository.save(questionInterview);
        return questionInterview;
    }

    public void deleteStudyQuestions(User user, Integer id) {
        Set<QuestionInterview> listStudyQuestion = user.getListStudyQuestion();
        QuestionInterview questionInterview = get(id).orElseThrow();
        listStudyQuestion.remove(questionInterview);
        userService.saveAndFlushUser(user);
    }

    public List<QuestionInterview> sortStudyQuestion(User user) {
        Set<QuestionInterview> listStudyQuestionInterview = user.getListStudyQuestion();
        List<QuestionInterview> list = new ArrayList<>(listStudyQuestionInterview);
        list.sort(Comparator
                .comparing(QuestionInterview::getId));
        return list;
    }

    public List<QuestionInterview> listFilling(User user) {
        if (user.getListQuestionInterviews().isEmpty()) {
            user.setListQuestionInterviews(createListQuestion());
            userService.saveAndFlushUser(user);
        }
        return sortQuestionList(user);
    }

    private List<QuestionInterview> sortQuestionList(User user) {
        Set<QuestionInterview> questionInterviews = user.getListQuestionInterviews();
        List<QuestionInterview> list = new ArrayList<>(questionInterviews);
        list.sort(Comparator
                .comparing(QuestionInterview::getTopic)
                .thenComparing(QuestionInterview::getId));
        return list;
    }

    private Set<QuestionInterview> createListQuestion() {
        Set<QuestionInterview> questionList = new HashSet<>();
        Set<Integer> integerSet = createRandomId(getAll().size());
        fillingInAListOfQuestions(integerSet, questionList);
        return questionList;
    }

    private void fillingInAListOfQuestions(Set<Integer> integerSet, Set<QuestionInterview> questionList) {
        for (Integer id : integerSet) {
            QuestionInterview questionInterview = get(id).orElseThrow();
            questionList.add(questionInterview);
        }
    }
}
