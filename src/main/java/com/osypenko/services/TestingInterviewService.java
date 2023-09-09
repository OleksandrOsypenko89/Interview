package com.osypenko.services;

import com.osypenko.model.testings.TestingInterview;
import com.osypenko.repository.TestingInterviewRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestingInterviewService {
    private final TestingInterviewRepo testingInterviewRepo;

    public List<TestingInterview> getAll() {
        return testingInterviewRepo.findAll();
    }

    public Optional<TestingInterview> get(int id) {
        return testingInterviewRepo.findById(id);
    }

    public TestingInterview save(TestingInterview testingInterview) {
        testingInterviewRepo.save(testingInterview);
        return testingInterview;
    }

    public Integer sizeAllQuestion() {
        return getAll().size();
    }
}
