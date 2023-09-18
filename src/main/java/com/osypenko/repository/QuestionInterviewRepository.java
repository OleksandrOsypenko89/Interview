package com.osypenko.repository;

import com.osypenko.model.interview.question.QuestionInterview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionInterviewRepository extends JpaRepository<QuestionInterview, Integer> {
}
