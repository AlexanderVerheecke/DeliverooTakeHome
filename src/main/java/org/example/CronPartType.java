package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CronPartType {
    MINUTES(0,59),
    HOURS(1,24),
    DAYOFMONTH(1,31),
    MONTHS(1,12),
    DAYOFWEEK(1,7);

    private int min;
    private int max;
}