package com.zenika.training.freenb.reservation.domain;

import java.time.LocalDate;

public record PeriodCriteria(LocalDate from, LocalDate to) {
    public static PeriodCriteria between(LocalDate from, LocalDate to) {
        return new PeriodCriteria(from, to);
    }
}
