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

@Slf4j
@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepository statisticRepository;

    public void delete(Statistic statistic) {
        statisticRepository.delete(statistic);
    }

    public void addStatistic(Statistic statistic) {
        statisticRepository.save(statistic);
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
        LocalDateTime resultDateTime = localDateTime.minusDays(10);
        return Timestamp.valueOf(resultDateTime);
    }

    public List<Statistic> sortStatistic(User user) {
        Set<Statistic> statistic = user.getStatistic();
        List<Statistic> list = new ArrayList<>(statistic);
        list.sort(Comparator
                .comparing(Statistic::getDate));
        return list;
    }

    public void saveNewStatistic(
            User user
            , Statistic statistic
            , int percentage
            , int knowAnswer
            , int sizeListQuestion
            , Type type
    ) {
        statistic.setResult(percentage);
        statistic.setUserId(user.getId());
        statistic.setType(type);
        statistic.setKnowAnswer(knowAnswer);
        statistic.setQuestions(sizeListQuestion);
        addStatistic(statistic);
    }

    public int result(User user) {
        Set<Statistic> userStatistic = user.getStatistic();
        if (userStatistic.isEmpty()) {
            return 0;
        }
        int size = userStatistic.size();
        int general = 0;
        for (Statistic statistic : userStatistic) {
            general += statistic.getResult();
        }
        return  general / size;
    }
}
