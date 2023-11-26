package com.zenika.training.freenb.reservation.domain.reservation;

import com.zenika.training.freenb.TestUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PeriodCriteriaTest {

    @Test
    void start_date_should_be_before_end_date() {
        assertThatThrownBy(() -> PeriodCriteria.between(TestUtils.NOV_2, TestUtils.NOV_1))
                .isInstanceOf(StartDateShouldBeBeforeEndDate.class);
    }

    @Test
    void should_be_possible_to_create_period_for_1_day() {
        PeriodCriteria period = PeriodCriteria.between(TestUtils.NOV_1, TestUtils.NOV_1);

        assertThat(period.from()).isEqualTo(period.to());
        assertThat(period.allDays().size()).isEqualTo(1);
    }
}
