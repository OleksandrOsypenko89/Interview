package com.osypenko.model.statistic;

import com.osypenko.services.statistics.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class StatisticRun implements Runnable {

    private final StatisticService statisticService;

    @Override
    public void run() {
        //noinspection InfiniteLoopStatement
        while (true) {
            try {
                //noinspection BusyWait
                Thread.sleep(43200000L);
                statisticService.deletionOfOutdatedStatistics();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
