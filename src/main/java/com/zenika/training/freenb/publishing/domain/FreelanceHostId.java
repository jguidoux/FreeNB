package com.zenika.training.freenb.publishing.domain;

import java.util.UUID;

public record FreelanceHostId(String value) {
    public static FreelanceHostId create() {
        return new FreelanceHostId(UUID.randomUUID().toString());
    }

}
