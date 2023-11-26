package com.zenika.training.freenb;

import com.zenika.training.freenb.reservation.domain.reservation.PeriodCriteria;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public class TestUtils {
    public static final LocalDate NOV_1 = LocalDate.of(2023, 11, 1);
    public static final LocalDate NOV_2 = LocalDate.of(2023, 11, 2);
    public static final LocalDate DEC_1 = LocalDate.of(2023, 12, 1);
    public static final Set<LocalDate> TWO_FIRST_DAYS_OF_NOVEMBER = Set.of(NOV_1, NOV_2);
    public static final PeriodCriteria TWO_FIRST_DAYS_OF_NOVEMBER_PERIOD = PeriodCriteria.between(NOV_1, NOV_2);

    public static final Set<LocalDate> NOVEMBER_DAYS = NOV_1.datesUntil(DEC_1)
                                                            .collect(Collectors.toSet());
}
