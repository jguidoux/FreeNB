package com.zenika.training.freenb.reservation.domain;

import java.util.UUID;

public record OfferId(String value) {
    public static OfferId create() {
        return new OfferId(UUID.randomUUID().toString());
    }
}
