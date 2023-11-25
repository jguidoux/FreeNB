package com.zenika.training.freenb.publishing.domain.offer;

import java.util.UUID;

public record IdOffer(String value) {
    public static IdOffer create() {
        return new IdOffer(UUID.randomUUID().toString());
    }
}
