package com.osypenko.services;

import com.osypenko.model.interview.QuestionInterview;
import com.osypenko.model.users.User;
import com.osypenko.repository.QuestionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepo questionRepo;
    private final UserService userService;

    public List<QuestionInterview> getAll() {
        return questionRepo.findAll();
    }

    public Optional<QuestionInterview> get(Integer id) {
        return questionRepo.findById(id);
    }

    public Integer sizeAllQuestion() {
        return getAll().size();
    }

    public Set<QuestionInterview> createListQuestion() {
        Set<Integer> integerSet = new HashSet<>();
        Set<QuestionInterview> questionList = new HashSet<>();

        createRandomIdQuestions(sizeAllQuestion(), integerSet);
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

    private void createRandomIdQuestions(int size, Set<Integer> integerSet) {
        do {
            Random random = new Random();
            int randomNum = random.nextInt((size - 1) + 1) + 1;
            integerSet.add(randomNum);
        } while (integerSet.size() < 20);
    }

    public void fillingInAListWithAQuestionToTheUser(User user) {
        Set<QuestionInterview> listQuestionInterviews = user.getListQuestionInterviews();
        if (listQuestionInterviews.isEmpty()) {
            user.setListQuestionInterviews(createListQuestion());
        }
        userService.createAndUpdateUser(user);
    }

    public void deleteStudyQuestions(User user, Integer id) {
        Set<QuestionInterview> listStudyQuestion = user.getListStudyQuestion();
        Optional<QuestionInterview> questionInterview = get(id);
        if (questionInterview.isPresent()) {
            QuestionInterview question = questionInterview.get();
            listStudyQuestion.remove(question);
        }
    }
}
