package com.zenika.training.freenb.reservation.domain.availableoffers;

import com.zenika.training.freenb.reservation.domain.reservation.PeriodCriteria;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static com.zenika.training.freenb.TestUtils.TWO_FIRST_DAYS_OF_NOVEMBER;
import static com.zenika.training.freenb.TestUtils.TWO_FIRST_DAYS_OF_NOVEMBER_PERIOD;
import static org.assertj.core.api.Assertions.assertThat;

class PlanningTest {


    @Test
    void should_contain_the_period() {

        Planning planning = Planning.fromListOfDays(TWO_FIRST_DAYS_OF_NOVEMBER, Seats.notFree());


        boolean contained = planning.containPeriod(TWO_FIRST_DAYS_OF_NOVEMBER_PERIOD);

        assertThat(contained).isTrue();

    }

    @Test
    void should_not_contain_the_period() {

        Planning planning = Planning.fromListOfDays(TWO_FIRST_DAYS_OF_NOVEMBER, Seats.notFree());

        LocalDate from = LocalDate.of(2023, 12, 1);
        LocalDate to = LocalDate.of(2023, 12, 2);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        boolean contained = planning.containPeriod(period);

        assertThat(contained).isFalse();

    }

    @Test
    void should_not_contain_the_period_when_last_days_is_upper_the_availabilities() {

        Planning planning = Planning.fromListOfDays(TWO_FIRST_DAYS_OF_NOVEMBER, Seats.notFree());

        LocalDate from = LocalDate.of(2023, 11, 1);
        LocalDate to = LocalDate.of(2023, 11, 3);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        boolean contained = planning.containPeriod(period);

        assertThat(contained).isFalse();

    }

    @Test
    void should_not_contain_the_period_when_first_days_is_below_the_availabilities() {

        Planning planning = Planning.fromListOfDays(TWO_FIRST_DAYS_OF_NOVEMBER, Seats.notFree());

        LocalDate from = LocalDate.of(2023, 10, 30);
        LocalDate to = LocalDate.of(2023, 11, 2);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        boolean contained = planning.containPeriod(period);

        assertThat(contained).isFalse();

    }

    @Test
    void should_not_contain_the_period_when_period_is_larger_than_availabilities() {
        
        Planning planning = Planning.fromListOfDays(TWO_FIRST_DAYS_OF_NOVEMBER, Seats.notFree());

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
        Planning planning = Planning.fromListOfDays(days, Seats.notFree());

        LocalDate from = LocalDate.of(2023, 11, 1);
        LocalDate to = LocalDate.of(2023, 11, 6);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        boolean contained = planning.containPeriod(period);

        assertThat(contained).isFalse();

    }

    @Test
    void should_decrement_seats_for_period() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        LocalDate day3 = LocalDate.of(2023, 11, 3);
        LocalDate day4 = LocalDate.of(2023, 11, 4);
        LocalDate day5 = LocalDate.of(2023, 11, 5);
        LocalDate day6 = LocalDate.of(2023, 11, 6);
        Set<LocalDate> days = Set.of(day1, day2, day3, day4, day5, day6);
        Planning planning = Planning.fromListOfDays(days, Seats.fromInt(1));

        LocalDate from = LocalDate.of(2023, 11, 3);
        LocalDate to = LocalDate.of(2023, 11, 4);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        planning.decrementSeatsFor(period);

        assertThat(planning.getSeatsOf(LocalDate.of(2023, 11, 1))).isEqualTo(Seats.fromInt(1));
        assertThat(planning.getSeatsOf(LocalDate.of(2023, 11, 2))).isEqualTo(Seats.fromInt(1));
        assertThat(planning.getSeatsOf(LocalDate.of(2023, 11, 3))).isEqualTo(Seats.fromInt(0));
        assertThat(planning.getSeatsOf(LocalDate.of(2023, 11, 4))).isEqualTo(Seats.fromInt(0));
        assertThat(planning.getSeatsOf(LocalDate.of(2023, 11, 5))).isEqualTo(Seats.fromInt(1));
        assertThat(planning.getSeatsOf(LocalDate.of(2023, 11, 6))).isEqualTo(Seats.fromInt(1));
    }


    @Test
    void should_increment_seats_for_period() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        LocalDate day3 = LocalDate.of(2023, 11, 3);
        LocalDate day4 = LocalDate.of(2023, 11, 4);
        LocalDate day5 = LocalDate.of(2023, 11, 5);
        LocalDate day6 = LocalDate.of(2023, 11, 6);
        Set<LocalDate> days = Set.of(day1, day2, day3, day4, day5, day6);
        Planning planning = Planning.fromListOfDays(days, Seats.fromInt(1));

        LocalDate from = LocalDate.of(2023, 11, 3);
        LocalDate to = LocalDate.of(2023, 11, 4);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        planning.incrementSeatsFor(period);

        assertThat(planning.getSeatsOf(LocalDate.of(2023, 11, 1))).isEqualTo(Seats.fromInt(1));
        assertThat(planning.getSeatsOf(LocalDate.of(2023, 11, 2))).isEqualTo(Seats.fromInt(1));
        assertThat(planning.getSeatsOf(LocalDate.of(2023, 11, 3))).isEqualTo(Seats.fromInt(2));
        assertThat(planning.getSeatsOf(LocalDate.of(2023, 11, 4))).isEqualTo(Seats.fromInt(2));
        assertThat(planning.getSeatsOf(LocalDate.of(2023, 11, 5))).isEqualTo(Seats.fromInt(1));
        assertThat(planning.getSeatsOf(LocalDate.of(2023, 11, 6))).isEqualTo(Seats.fromInt(1));
    }

    @Test
    void should_have_free_teas() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        LocalDate day3 = LocalDate.of(2023, 11, 3);
        LocalDate day4 = LocalDate.of(2023, 11, 5);
        LocalDate day5 = LocalDate.of(2023, 11, 6);
        Set<LocalDate> days = Set.of(day1, day2, day3, day4, day5);
        Planning planning = Planning.fromListOfDays(days, Seats.fromInt(1));

        LocalDate from = LocalDate.of(2023, 11, 1);
        LocalDate to = LocalDate.of(2023, 11, 6);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        boolean contained = planning.hasFreeSeatsFor(period);

        assertThat(contained).isTrue();

    }

    @Test
    void should_not_have_free_seats_when_period_not_present_in_planning() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        LocalDate day3 = LocalDate.of(2023, 11, 3);
        LocalDate day4 = LocalDate.of(2023, 11, 5);
        LocalDate day5 = LocalDate.of(2023, 11, 6);
        Set<LocalDate> days = Set.of(day1, day2, day3, day4, day5);
        Planning planning = Planning.fromListOfDays(days, Seats.fromInt(1));

        LocalDate from = LocalDate.of(2023, 12, 1);
        LocalDate to = LocalDate.of(2023, 12, 6);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        boolean contained = planning.hasFreeSeatsFor(period);

        assertThat(contained).isFalse();

    }

    @Test
    void should_not_have_free_seats_when_all_seats_are_occupied() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        LocalDate day3 = LocalDate.of(2023, 11, 3);
        LocalDate day4 = LocalDate.of(2023, 11, 5);
        LocalDate day5 = LocalDate.of(2023, 11, 6);
        Set<LocalDate> days = Set.of(day1, day2, day3, day4, day5);
        Planning planning = Planning.fromListOfDays(days, Seats.fromInt(0));

        LocalDate from = LocalDate.of(2023, 12, 1);
        LocalDate to = LocalDate.of(2023, 12, 6);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        boolean contained = planning.hasFreeSeatsFor(period);

        assertThat(contained).isFalse();

    }

    @Test
    void should_not_have_free_seats_when_one_of_days_is_full() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        LocalDate day3 = LocalDate.of(2023, 11, 3);
        LocalDate day4 = LocalDate.of(2023, 11, 5);
        LocalDate day5 = LocalDate.of(2023, 11, 6);
        Set<LocalDate> days = Set.of(day1, day2, day3, day4, day5);
        Planning planning = Planning.fromListOfDays(days, Seats.fromInt(1));

        planning.decrementSeatsFor(PeriodCriteria.fromDay(LocalDate.of(2023, 11, 3)));

        LocalDate from = LocalDate.of(2023, 12, 1);
        LocalDate to = LocalDate.of(2023, 12, 6);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        boolean contained = planning.hasFreeSeatsFor(period);

        assertThat(contained).isFalse();

    }
}