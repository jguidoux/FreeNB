package com.zenika.training.freenb.publishing.domain;

import java.util.UUID;

public record IdFreelanceHost(String value) {
    public static IdFreelanceHost create() {
        return new IdFreelanceHost(UUID.randomUUID().toString());
    }
}
