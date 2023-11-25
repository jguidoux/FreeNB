package com.zenika.training.freenb.publishing.domain.offer;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public record OfferPeriod(LocalDate start, LocalDate end) {
    public static OfferPeriod between(LocalDate start, LocalDate end) {
        return new OfferPeriod(start, end);
    }

    public List<LocalDate> listOfDays() {
        return Stream.concat(
                start.datesUntil(end),
                Stream.of(end)
        ).toList();
    }
}
