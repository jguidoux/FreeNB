package com.zenika.training.freenb.publishing.domain.workspace;

public record Capacity(int value) {
    static Capacity empty() {
        return new Capacity(0);
    }

    Capacity add(Capacity otherCapacity) {
        return new Capacity(otherCapacity.value() + value());
    }
}
