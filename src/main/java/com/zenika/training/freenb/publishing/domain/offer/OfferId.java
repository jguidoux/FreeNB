package com.zenika.training.freenb.publishing.domain.offer;

import java.util.UUID;

public record OfferId(String value) {
    public static OfferId create() {
        return new OfferId(UUID.randomUUID().toString());
    }
}
