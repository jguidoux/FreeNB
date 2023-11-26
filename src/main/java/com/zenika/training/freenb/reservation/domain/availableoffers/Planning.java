package com.zenika.training.freenb.reservation.domain.availableoffers;

import com.zenika.training.freenb.reservation.domain.PeriodCriteria;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Planning {
    private final Map<LocalDate, Seats> days;

    public Planning(Set<LocalDate> days, Seats seats) {

        this.days = days.stream().collect(Collectors.toMap(day -> day, day -> seats));
    }

    public static Planning fromListOfDays(Set<LocalDate> days, Seats seats) {
        return new Planning(days, seats);
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
        return days.keySet().containsAll(allPeriodDays);
    }

    public Seats getSeatsOf(LocalDate date) {
        return days.get(date);
    }

    public void decrementSeatsFor(PeriodCriteria period) {
        period.allDays().forEach(this::decrementSeatFor);

    }

    private void decrementSeatFor(LocalDate day) {
        Seats seats = days.get(day);
        days.put(day, seats.decrement());
    }

    public void incrementSeatsFor(PeriodCriteria period) {
        period.allDays().forEach(this::incrementSeatsFor);

    }

    private void incrementSeatsFor(LocalDate day) {
        Seats seats = days.get(day);
        days.put(day, seats.increment());
    }

    public boolean hasFreeSeatsFor(PeriodCriteria period) {
        Set<Seats> correspondingSeats = period.allDays().stream().map(days::get).filter(Objects::nonNull)
                                              .collect(Collectors.toSet());
        if (correspondingSeats.isEmpty()) {
            return false;
        }
        return correspondingSeats.stream().noneMatch(Seats::noneFree);
    }
}
