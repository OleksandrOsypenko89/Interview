package com.osypenko.config;

import com.osypenko.services.statistics.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Config {

    private final StatisticService statisticService;

    @Bean
    public void timeDeleteStatistics() {
        Thread thread = new Thread(statisticService);
        thread.start();
    }
}
