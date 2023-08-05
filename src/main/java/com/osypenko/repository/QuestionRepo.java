package com.osypenko.repository;

import com.osypenko.model.interview.QuestionInterview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<QuestionInterview, Integer> {
}
