package com.zenika.training.freenb.reservation.domain.availableoffers;

public record Seats(int count) {
    public static Seats fromInt(int availableSeats) {
        return new Seats(availableSeats);
    }

    public static Seats notFree() {
        return new Seats(0);
    }

    public Seats decrement() {
        return new Seats(Math.max(0, count() - 1));
    }

    public boolean haveFreePlaces() {
        return count != 0;
    }

    public Seats increment() {
        return new Seats(count + 1);
    }

    public boolean noneFree() {
        return count == 0;
    }
}
