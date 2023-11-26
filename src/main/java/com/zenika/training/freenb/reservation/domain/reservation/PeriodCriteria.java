package com.zenika.training.freenb.reservation.domain.reservation;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public record PeriodCriteria(LocalDate from, LocalDate to) {
    public static PeriodCriteria between(LocalDate from, LocalDate to) {
        if (to.isBefore(from)) {
            throw new StartDateShouldBeBeforeEndDate();
        }
        return new PeriodCriteria(from, to);
    }

    public static PeriodCriteria fromDay(LocalDate date) {
        return new PeriodCriteria(date, date);
    }

    public Set<LocalDate> allDays() {
        return from().datesUntil(to().plusDays(1))
                     .collect(Collectors.toUnmodifiableSet());
    }
}
