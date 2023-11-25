package com.zenika.training.freenb.publishing.domain.offer;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class OfferPeriodTest {

    @Test
    void should_return_list_of_day_for_period() {
        LocalDate from = LocalDate.of(2023, 11, 1);
        LocalDate to = LocalDate.of(2023, 11, 7);
        OfferPeriod period = OfferPeriod.between(from, to);

        Set<LocalDate> days = period.listOfDays();

        assertThat(days).hasSize(7);
    }
}