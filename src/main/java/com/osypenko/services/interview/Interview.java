package com.osypenko.services.interview;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static com.osypenko.constant.Constant.*;

public abstract class Interview {

    public int getPercentage(int know, int sizeListQuestion) {
        return (know * ONE_HUNDRED) / sizeListQuestion;
    }

    public Set<Integer> createRandomId(int size) {
        Set<Integer> integerSet = new HashSet<>();
        do {
            Random random = new Random();
            int randomNum = random.nextInt(size);
            if (randomNum != ZERO) {
                integerSet.add(randomNum);
            }
        } while (integerSet.size() < SIZE_QUESTION);
        return integerSet;
    }
}
