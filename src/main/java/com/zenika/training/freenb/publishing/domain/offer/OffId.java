package com.zenika.training.freenb.publishing.domain.offer;

import java.util.UUID;

public record OffId(String value) {
    public static OffId create() {
        return new OffId(UUID.randomUUID().toString());
    }
}
