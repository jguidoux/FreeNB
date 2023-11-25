package com.zenika.training.freenb.reservation.domain;

import java.util.UUID;

public record HostId(String value) {
    public static HostId fromString(String id) {
        return new HostId(id);
    }

    public static HostId create() {
        return HostId.fromString(UUID.randomUUID().toString());
    }
}
