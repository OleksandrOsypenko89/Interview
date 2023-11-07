package com.osypenko.config;

import com.osypenko.model.statistic.StatisticRun;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Config {
    private final StatisticRun statisticRun;

    @Bean
    public void timeDeleteStatistics() {
        Thread thread = new Thread(statisticRun);
        thread.start();
    }
}
