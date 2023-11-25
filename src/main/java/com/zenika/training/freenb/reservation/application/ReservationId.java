package com.zenika.training.freenb.reservation.application;

import java.util.UUID;

public record ReservationId(String value) {

    public static  ReservationId create() {
        return new ReservationId(UUID.randomUUID().toString());
    }

    public static ReservationId fromString(String reservationId) {
        return new ReservationId(reservationId);
    }
}
