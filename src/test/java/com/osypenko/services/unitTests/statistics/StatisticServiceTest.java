package com.osypenko.services.unitTests.statistics;

import com.osypenko.model.statistic.Statistic;
import com.osypenko.model.statistic.Type;
import com.osypenko.repository.StatisticRepository;
import com.osypenko.services.statistics.StatisticService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class StatisticServiceTest {

    @Mock
    StatisticRepository statisticRepository;
    @InjectMocks
    StatisticService statisticService;

    private Statistic statisticOne;
    private Statistic statisticTwo;
    private static final long ID_ONE = 1L;
    private static final long ID_TWO = 2L;
    private static final int KNOW = 10;
    private static final int SIZE_QUESTIONS = 15;

    @BeforeEach
    void setup() {
        statisticOne = Statistic.builder()
                .id(ID_ONE)
                .date(new Timestamp(System.currentTimeMillis()))
                .userId(ID_ONE)
                .type(Type.TESTING)
                .knowAnswer(KNOW)
                .questions(SIZE_QUESTIONS)
                .build();
        statisticTwo = Statistic.builder()
                .id(ID_TWO)
                .date(new Timestamp(System.currentTimeMillis()))
                .userId(ID_ONE)
                .type(Type.QUESTIONS)
                .knowAnswer(KNOW)
                .questions(SIZE_QUESTIONS)
                .build();
    }

    @Test
    void allStatistics() {
        Mockito.doAnswer(invocationOnMock -> List.of(statisticOne, statisticTwo))
                .when(statisticRepository)
                .findAll();
        List<Statistic> statistics = statisticService.allStatistics();
        Assertions.assertEquals(2, statistics.size());
        Mockito.verify(statisticRepository, Mockito.times(1)).findAll();
        Mockito.verifyNoMoreInteractions(statisticRepository);
    }

    @Test
    void delete() {
        Mockito.doNothing()
                .when(statisticRepository)
                .delete(statisticOne);
        statisticService.delete(statisticOne);
        Mockito.verify(statisticRepository, Mockito.times(1)).delete(statisticOne);
        Mockito.verifyNoMoreInteractions(statisticRepository);
    }

    @Test
    void addStatistic() {
        Mockito.doAnswer(invocationOnMock -> statisticOne)
                .when(statisticRepository)
                .save(statisticOne);
        statisticService.addStatistic(statisticOne);
        Mockito.verify(statisticRepository, Mockito.times(1)).save(statisticOne);
        Mockito.verifyNoMoreInteractions(statisticRepository);
    }
}