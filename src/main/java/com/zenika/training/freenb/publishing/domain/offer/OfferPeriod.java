package com.zenika.training.freenb.publishing.domain.offer;

import java.time.LocalDate;

public record OfferPeriod(LocalDate start, LocalDate end) {
    public static OfferPeriod between(LocalDate start , LocalDate end) {
        return new OfferPeriod(start, end);
    }
}
