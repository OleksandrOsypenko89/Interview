package com.osypenko.repository;

import com.osypenko.model.interview.testings.TestingInterview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestingInterviewRepository extends JpaRepository<TestingInterview, Integer> {
}
