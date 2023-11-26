package com.zenika.training.freenb.reservation.domain.availableoffers;

import com.zenika.training.freenb.reservation.domain.PeriodCriteria;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Planning {
    private final Set<LocalDate> days;

    public Planning(Set<LocalDate> days) {

        this.days = days;
    }

    public static Planning fromListOfDays(Set<LocalDate> days) {
        return new Planning(days);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planning planning = (Planning) o;
        return Objects.equals(days, planning.days);
    }

    @Override
    public int hashCode() {
        return Objects.hash(days);
    }

    public boolean containPeriod(PeriodCriteria period) {
        Set<LocalDate> allPeriodDays = period.from().datesUntil(period.to().plusDays(1))
                                       .collect(Collectors.toUnmodifiableSet());
        return days.containsAll(allPeriodDays);
    }
}
