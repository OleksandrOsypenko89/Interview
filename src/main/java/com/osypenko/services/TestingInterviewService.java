package com.osypenko.services;

import com.osypenko.model.testings.TestingInterview;
import com.osypenko.repository.TestingRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestingInterviewService {
    private final TestingRepo testingRepo;

    public List<TestingInterview> getAll() {
         return testingRepo.findAll();
    }
}
