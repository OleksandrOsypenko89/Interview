package com.osypenko.services;

import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.users.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticService {
    private final UserService userService;
    public void deletionOfOutdatedStatistics(User user) {
        List<Statistic> statistics = user.getStatistic();
        Timestamp limit = timeLimitForDeletion();
        for (Statistic statistic : statistics) {
            if (statistic.getDate().after(limit)) {
                statistics.remove(statistic);
            }
            userService.createAndUpdateUser(user);
        }
    }
    private Timestamp timeLimitForDeletion() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        LocalDateTime resultDateTime = localDateTime.minusDays(7);
        return Timestamp.valueOf(resultDateTime);
    }
}
