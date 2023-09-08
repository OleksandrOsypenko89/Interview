package com.osypenko.repository;

import com.osypenko.model.testings.TestingInterview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestingRepo extends JpaRepository<TestingInterview, Integer> {
}
