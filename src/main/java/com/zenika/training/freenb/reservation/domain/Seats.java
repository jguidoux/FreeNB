package com.zenika.training.freenb.reservation.domain;

public record Seats(int count) {
    public static Seats fromInt(int availableSeats) {
        return new Seats(availableSeats);
    }

    Seats decrement() {
        return new Seats(count() - 1);
    }

    public boolean areAllOccupied() {
        return count == 0;
    }
}
