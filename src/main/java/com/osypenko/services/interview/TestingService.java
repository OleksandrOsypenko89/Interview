package com.osypenko.services.interview;

import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.model.users.User;
import com.osypenko.repository.TestingInterviewRepository;
import com.osypenko.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestingService extends Interview {
    private final UserService userService;
    private final TestingInterviewRepository testingInterviewRepository;

    public List<TestingInterview> getAll() {
        return testingInterviewRepository.findAll();
    }

    public Optional<TestingInterview> get(int id) {
        return testingInterviewRepository.findById(id);
    }

    public TestingInterview save(TestingInterview testingInterview) {
        return testingInterviewRepository.save(testingInterview);
    }

    public Integer sizeAllQuestion() {
        return getAll().size();
    }

    public List<TestingInterview> listFilling(User user) {
        if (user.getListQuestionTesting().isEmpty()) {
            user.setListQuestionTesting(createListQuestion());
            userService.flushUser(user);
        }
        Set<TestingInterview> userListQuestionTesting = user.getListQuestionTesting();
        return new ArrayList<>(userListQuestionTesting);
    }

    public Set<TestingInterview> createListQuestion() {
        Set<Integer> integerSet = new HashSet<>();
        Set<TestingInterview> questionList = new HashSet<>();

        createRandomId(sizeAllQuestion(), integerSet);
        fillingInAListOfQuestions(integerSet, questionList);
        return questionList;
    }

    private void fillingInAListOfQuestions(Set<Integer> integerSet, Set<TestingInterview> questionList) {
        for (Integer id : integerSet) {
            TestingInterview questionTesting = get(id).orElseThrow();
            questionList.add(questionTesting);
        }
    }

    public List<String> shuffleButtons(TestingInterview testingInterview) {
        List<String> randomButton = new ArrayList<>();
        randomButton.add(testingInterview.getCorrectAnswer());
        randomButton.add(testingInterview.getFirstFalseAnswer());
        randomButton.add(testingInterview.getSecondFalseAnswer());
        randomButton.add(testingInterview.getThirdFalseAnswer());
        randomButton.add(testingInterview.getFourthFalseAnswer());
        randomButton.add(testingInterview.getFifthFalseAnswer());
        Collections.shuffle(randomButton);
        return randomButton;
    }
}
