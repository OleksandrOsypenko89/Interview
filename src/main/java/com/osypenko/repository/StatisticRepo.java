package com.osypenko.repository;

import com.osypenko.model.statistic.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepo extends JpaRepository<Statistic, Long> {
}
