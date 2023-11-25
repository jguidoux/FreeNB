package com.zenika.training.freenb.reservation.domain.availableoffers;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

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
}
