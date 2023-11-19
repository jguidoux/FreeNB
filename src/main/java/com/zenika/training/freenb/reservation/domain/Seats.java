package com.zenika.training.freenb.reservation.domain;

public record Seats(int availableSeats) {
    public static Seats fromInt(int availableSeats) {
        return new Seats(availableSeats);
    }

    public Seats decrement() {
        return new Seats(availableSeats -1);
    }
}
