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

    public Integer sizeAllQuestion() {
        return getAll().size();
    }

    public List<QuestionInterview> listFilling(User user) {
        if (user.getListQuestionInterviews().isEmpty()) {
            user.setListQuestionInterviews(createListQuestion());
            userService.flushUser(user);
        }
        return sortQuestionList(user);
    }

    public List<QuestionInterview> sortQuestionList(User user) {
        Set<QuestionInterview> questionInterviews = user.getListQuestionInterviews();
        List<QuestionInterview> list = new ArrayList<>(questionInterviews);
        list.sort(Comparator
                .comparing(QuestionInterview::getTopic)
                .thenComparing(QuestionInterview::getId));
        return list;
    }

    public Set<QuestionInterview> createListQuestion() {
        Set<Integer> integerSet = new HashSet<>();
        Set<QuestionInterview> questionList = new HashSet<>();

        createRandomId(sizeAllQuestion(), integerSet);
        fillingInAListOfQuestions(integerSet, questionList);
        return questionList;
    }

    private void fillingInAListOfQuestions(Set<Integer> integerSet, Set<QuestionInterview> questionList) {
        for (Integer id : integerSet) {
            Optional<QuestionInterview> questionInterview = get(id);
            if (questionInterview.isPresent()) {
                QuestionInterview interview = questionInterview.get();
                questionList.add(interview);
            }
        }
    }

    public void deleteStudyQuestions(User user, Integer id) {
        Set<QuestionInterview> listStudyQuestion = user.getListStudyQuestion();
        Optional<QuestionInterview> questionInterview = get(id);
        if (questionInterview.isPresent()) {
            QuestionInterview question = questionInterview.get();
            listStudyQuestion.remove(question);
        }
        userService.createAndUpdateUser(user);
    }

    public void sortStudyQuestion(User user) {
        Set<QuestionInterview> listStudyQuestionInterview = user.getListStudyQuestion();
        List<QuestionInterview> list = new ArrayList<>(listStudyQuestionInterview);
        list.sort(Comparator
                .comparing(QuestionInterview::getId));
    }
}
