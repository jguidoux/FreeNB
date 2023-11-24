package com.zenika.training.freenb.reservation.domain;

import com.zenika.training.freenb.reservation.application.Reservation;
import com.zenika.training.shared.AggregateRoot;

public class AvailableOffer extends AggregateRoot<OfferId> {

    private Seats availableSeats;

    public AvailableOffer(OfferId offerId, Seats seats) {
        super(offerId);
        this.availableSeats = seats;
    }

    public Seats getAvailableSeats() {
        return availableSeats;
    }

    public Reservation book() {
        Reservation reservation = new Reservation(id);
        availableSeats = availableSeats.decrement();
        return reservation;
    }

    public boolean isApproved() {
        return false;
    }

    public void bookRefused() {
        availableSeats = availableSeats.increment();
    }
}
