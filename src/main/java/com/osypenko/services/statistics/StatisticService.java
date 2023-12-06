package com.osypenko.services.statistics;

import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.statistic.Type;
import com.osypenko.model.users.User;
import com.osypenko.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static com.osypenko.constant.Constant.*;
import static com.osypenko.constant.NameLogs.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticService implements Runnable {
    private final StatisticRepository statisticRepository;

    public List<Statistic> allStatistics() {
        return statisticRepository.findAll();
    }

    public void delete(Statistic statistic) {
        statisticRepository.delete(statistic);
    }

    public void deletionOfOutdatedStatistics() {
        List<Statistic> statistics = allStatistics();
        Timestamp limit = timeLimitForDeletion();
        for (Statistic statistic : statistics) {
            if (statistic.getDate().before(limit)) {
                delete(statistic);
            }
        }
    }

    public Timestamp timeLimitForDeletion() {
        LocalDateTime newDateTime = new Timestamp(System.currentTimeMillis()).toLocalDateTime();
        LocalDateTime resultDateTime = newDateTime.minusDays(MEMBER_STATISTIC_DAYS);
        return Timestamp.valueOf(resultDateTime);
    }

    public List<Statistic> sortStatistic(User user) {
        Set<Statistic> statistic = user.getStatistic();
        List<Statistic> list = new ArrayList<>(statistic);
        list.sort(Comparator.comparing(Statistic::getDate));
        return list;
    }

    public Statistic createNewStatistic(
            User user
            , int percentage
            , int knowAnswer
            , Type type
    ) {
        Statistic statistic = new Statistic();
        statistic.setResult(percentage);
        statistic.setUserId(user.getId());
        statistic.setType(type);
        statistic.setKnowAnswer(knowAnswer);
        statistic.setDate(new Timestamp(System.currentTimeMillis()));
        statisticRepository.save(statistic);
        log.info(CREATE_NEW_STATISTIC);
        return statistic;
    }

    public int result(User user) {
        Set<Statistic> userStatistic = user.getStatistic();
        if (userStatistic.isEmpty()) {
            return ZERO;
        }
        int size = userStatistic.size();
        int general = ZERO;
        for (Statistic statistic : userStatistic) {
            general += statistic.getResult();
        }
        return general / size;
    }

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                //noinspection BusyWait
                Thread.sleep(TWELVE_HOURS);
                deletionOfOutdatedStatistics();
                log.info(DELETION_OF_OUTDATED_STATISTICS);
            } catch (InterruptedException e) {
                log.error(ERROR_IN_A_SEPARATE_STREAM_THAT_SERVES_FOR_SCHEDULED_CLEANING_OF_STATISTICS);
                throw new RuntimeException(e);
            }
        }
    }
}