package com.zenika.training.freenb.reservation.domain.availableoffers;

import java.util.UUID;

public record OfferId(String value) {
    public static OfferId create() {
        return new OfferId(UUID.randomUUID().toString());
    }
}
