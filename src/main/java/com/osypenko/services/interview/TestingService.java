package com.osypenko.services.interview;

import com.osypenko.model.interview.testings.TestingInterview;
import com.osypenko.model.users.User;
import com.osypenko.repository.TestingInterviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestingService {
    private final TestingInterviewRepository testingInterviewRepository;

    public List<TestingInterview> getAll() {
        return testingInterviewRepository.findAll();
    }

    public Optional<TestingInterview> get(int id) {
        return testingInterviewRepository.findById(id);
    }

    public TestingInterview save(TestingInterview testingInterview) {
        testingInterviewRepository.save(testingInterview);
        return testingInterview;
    }

    public Integer sizeAllQuestion() {
        return getAll().size();
    }

    public Set<TestingInterview> createListQuestion() {
        Set<Integer> integerSet = new HashSet<>();
        Set<TestingInterview> questionList = new HashSet<>();

        createRandomIdQuestions(sizeAllQuestion(), integerSet);
        fillingInAListOfQuestions(integerSet, questionList);
        return questionList;
    }

    private void fillingInAListOfQuestions(Set<Integer> integerSet, Set<TestingInterview> questionList) {
        for (Integer id : integerSet) {
            Optional<TestingInterview> questionTesting = get(id);
            if (questionTesting.isPresent()) {
                TestingInterview interview = questionTesting.get();
                questionList.add(interview);
            }
        }
    }

    private void createRandomIdQuestions(int size, Set<Integer> integerSet) {
        do {
            Random random = new Random();
            int randomNum = random.nextInt((size - 1) + 1) + 1;
            integerSet.add(randomNum);
        } while (integerSet.size() < 15);
    }

    public List<TestingInterview> sortTestingList(User user) {
        Set<TestingInterview> questionTesting = user.getListQuestionTesting();
        List<TestingInterview> list = new ArrayList<>(questionTesting);
        list.sort(Comparator.comparing(TestingInterview::getId));
        return list;
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
