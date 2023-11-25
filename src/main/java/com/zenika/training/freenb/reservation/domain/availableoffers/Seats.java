package com.zenika.training.freenb.reservation.domain.availableoffers;

public record Seats(int count) {
    public static Seats fromInt(int availableSeats) {
        return new Seats(availableSeats);
    }

    Seats decrement() {
        return new Seats(count() - 1);
    }

    public boolean haveFreePlaces() {
        return count != 0;
    }

    public Seats increment() {
        return new Seats(count + 1);
    }
}
