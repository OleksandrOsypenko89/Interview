package com.osypenko.services;

import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.statistic.Type;
import com.osypenko.model.users.User;
import com.osypenko.repository.StatisticRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepo statisticRepo;

    public void delete(Statistic statistic) {
        statisticRepo.delete(statistic);
    }

    public void addStatistic(Statistic statistic) {
        statisticRepo.save(statistic);
    }

    public void deletionOfOutdatedStatistics(User user) {
        Set<Statistic> statistics = user.getStatistic();
        Timestamp limit = timeLimitForDeletion();
        for (Statistic statistic : statistics) {
            if (statistic.getDate().before(limit)) {
                delete(statistic);
            }
        }
    }

    private Timestamp timeLimitForDeletion() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        LocalDateTime resultDateTime = localDateTime.minusDays(7);
        return Timestamp.valueOf(resultDateTime);
    }

    public List<Statistic> sortStatistic(User user) {
        Set<Statistic> statistic = user.getStatistic();
        List<Statistic> list = new ArrayList<>(statistic);
        list.sort(Comparator
                .comparing(Statistic::getDate));
        return list;
    }

    public void saveNewStatistic(User user, Statistic statistic, int percentage) {
        statistic.setResult(percentage);
        statistic.setUserId(user.getId());
        statistic.setType(Type.INTERVIEW);
        addStatistic(statistic);
    }
}