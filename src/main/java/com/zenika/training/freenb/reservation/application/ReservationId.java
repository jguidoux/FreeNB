package com.zenika.training.freenb.reservation.application;

import java.util.UUID;

public record ReservationId(String string) {

    public static  ReservationId create() {
        return new ReservationId(UUID.randomUUID().toString());
    }
}
