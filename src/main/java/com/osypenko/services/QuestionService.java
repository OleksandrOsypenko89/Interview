package com.osypenko.services;

import com.osypenko.model.interview.QuestionInterview;
import com.osypenko.repository.QuestionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class QuestionService {
    private QuestionRepo questionRepo;
    public Optional<QuestionInterview> get(Integer id) {
        return questionRepo.findById(id);
    }

}
