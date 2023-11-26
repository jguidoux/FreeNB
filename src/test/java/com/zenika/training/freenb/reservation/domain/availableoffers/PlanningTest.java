package com.zenika.training.freenb.reservation.domain.availableoffers;

import com.zenika.training.freenb.reservation.domain.PeriodCriteria;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class PlanningTest {


    @Test
    void should_contain_the_period() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        Set<LocalDate> days = Set.of(day1, day2);
        Planning planning = Planning.fromListOfDays(days);

        LocalDate from = LocalDate.of(2023, 11, 1);
        LocalDate to = LocalDate.of(2023, 11, 2);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        boolean contained = planning.containPeriod(period);

        assertThat(contained).isTrue();

    }

    @Test
    void should_not_contain_the_period() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        Set<LocalDate> days = Set.of(day1, day2);
        Planning planning = Planning.fromListOfDays(days);

        LocalDate from = LocalDate.of(2023, 12, 1);
        LocalDate to = LocalDate.of(2023, 12, 2);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        boolean contained = planning.containPeriod(period);

        assertThat(contained).isFalse();

    }

    @Test
    void should_not_contain_the_period_when_last_days_is_upper_the_availabilities() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        Set<LocalDate> days = Set.of(day1, day2);
        Planning planning = Planning.fromListOfDays(days);

        LocalDate from = LocalDate.of(2023, 11, 1);
        LocalDate to = LocalDate.of(2023, 11, 3);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        boolean contained = planning.containPeriod(period);

        assertThat(contained).isFalse();

    }

    @Test
    void should_not_contain_the_period_when_first_days_is_below_the_availabilities() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        Set<LocalDate> days = Set.of(day1, day2);
        Planning planning = Planning.fromListOfDays(days);

        LocalDate from = LocalDate.of(2023, 10, 30);
        LocalDate to = LocalDate.of(2023, 11, 2);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        boolean contained = planning.containPeriod(period);

        assertThat(contained).isFalse();

    }

    @Test
    void should_not_contain_the_period_when_period_is_larger_than_availabilities() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        Set<LocalDate> days = Set.of(day1, day2);
        Planning planning = Planning.fromListOfDays(days);

        LocalDate from = LocalDate.of(2023, 10, 30);
        LocalDate to = LocalDate.of(2023, 11, 3);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        boolean contained = planning.containPeriod(period);

        assertThat(contained).isFalse();

    }

    @Test
    void should_not_contain_the_period_when_there_are_missing_date_in_availabilities() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        LocalDate day3 = LocalDate.of(2023, 11, 3);
        LocalDate day4 = LocalDate.of(2023, 11, 5);
        LocalDate day5 = LocalDate.of(2023, 11, 6);
        Set<LocalDate> days = Set.of(day1, day2, day3, day4, day5);
        Planning planning = Planning.fromListOfDays(days);

        LocalDate from = LocalDate.of(2023, 11, 1);
        LocalDate to = LocalDate.of(2023, 11, 6);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        boolean contained = planning.containPeriod(period);

        assertThat(contained).isFalse();

    }
}