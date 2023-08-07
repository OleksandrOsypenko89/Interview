package com.osypenko.services;

import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.users.User;
import com.osypenko.repository.StatisticRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepo statisticRepo;

    public void delete(Statistic statistic) {
        statisticRepo.delete(statistic);
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
}
