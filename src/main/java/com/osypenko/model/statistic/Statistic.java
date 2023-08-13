package com.osypenko.model.statistic;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "statistic")
@ToString
public class Statistic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp date = new Timestamp(System.currentTimeMillis());

    private Integer result;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    private Type type;
}


