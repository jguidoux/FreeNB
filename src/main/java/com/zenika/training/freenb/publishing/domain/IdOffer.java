package com.zenika.training.freenb.publishing.domain;

import java.util.UUID;

public record IdOffer(String string) {
    public static IdOffer create() {
        return new IdOffer(UUID.randomUUID().toString());
    }
}
