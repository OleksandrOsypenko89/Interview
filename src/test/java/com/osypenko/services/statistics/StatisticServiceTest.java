package com.osypenko.services.statistics;

import com.osypenko.BaseTests;
import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.statistic.Type;
import com.osypenko.model.users.User;
import com.osypenko.services.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.osypenko.constant.Constant.SIZE_QUESTION;

class StatisticServiceTest extends BaseTests {
    private final StatisticService statisticService;
    private final UserService userService;

    public StatisticServiceTest(StatisticService statisticService, UserService userService) {
        this.statisticService = statisticService;
        this.userService = userService;
    }

    @Test
    void allStatistics() {
        List<Statistic> statistics = statisticService.allStatistics();
        Assertions.assertEquals(ALL_STATISTIC_SIZE, statistics.size());
    }

    @Test
    void delete() {
        List<Statistic> statistics = statisticService.allStatistics();
        Statistic stat = statistics.get(1);
        statisticService.delete(stat);
        int size = statisticService.allStatistics().size();
        Assertions.assertEquals(1, size);
    }

    @Test
    void deletionOfOutdatedStatistics() {
        statisticService.deletionOfOutdatedStatistics();
        List<Statistic> statistics = statisticService.allStatistics();
        Assertions.assertEquals(0, statistics.size());
    }

    @Test
    void sortStatistic() {
        User user = userService.findByEmail(EXPECTED_USER_EMAIL).orElseThrow();
        List<Statistic> statistics = statisticService.sortStatistic(user);
        Assertions.assertEquals(1, statistics.get(0).getId());
        Assertions.assertEquals(2, statistics.get(1).getId());
    }

    @Test
    void createNewStatistic() {
        User user = userService.findByEmail(EXPECTED_USER_EMAIL).orElseThrow();
        statisticService.createNewStatistic(user, TEST_RESULT_STATISTIC, SIZE_QUESTION, Type.QUESTIONS);
        Assertions.assertEquals(3, statisticService.allStatistics().size());
    }

    @Test
    void result() {
        User user = userService.findByEmail(EXPECTED_USER_EMAIL).orElseThrow();
        int result = statisticService.result(user);
        Assertions.assertEquals(60, result);
    }
}