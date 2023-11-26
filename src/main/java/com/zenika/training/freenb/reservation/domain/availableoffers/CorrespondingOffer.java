package com.zenika.training.freenb.reservation.domain.availableoffers;

import com.zenika.training.freenb.reservation.domain.reservation.PeriodCriteria;

public record CorrespondingOffer(OfferId id, PeriodCriteria period) {
}
