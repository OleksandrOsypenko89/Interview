package com.osypenko.dto;

import com.osypenko.model.statistic.Type;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {
    private Long id;
    private Timestamp date;
    private Integer result;
    private Type type;
    private Integer knowAnswer;
    private Integer questions;
}
