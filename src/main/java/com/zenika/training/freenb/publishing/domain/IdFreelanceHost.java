package com.zenika.training.freenb.publishing.domain;

import java.util.UUID;

public record IdFreelanceHost(String value) {
    public static IdFreelanceHost create() {
        return new IdFreelanceHost(UUID.randomUUID().toString());
    }

    public static IdFreelanceHost fromString(String hostId) {
        return new IdFreelanceHost(hostId);
    }
}
