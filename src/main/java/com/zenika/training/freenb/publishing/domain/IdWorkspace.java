package com.zenika.training.freenb.publishing.domain;

import java.util.UUID;

public record IdWorkspace(String value) {
    public static IdWorkspace create() {
        return new IdWorkspace(UUID.randomUUID().toString());
    }
}
