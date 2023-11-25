package com.zenika.training.freenb.reservation.domain.availableoffers;

import com.zenika.training.freenb.reservation.domain.HostId;
import com.zenika.training.freenb.reservation.domain.reservation.Reservation;
import com.zenika.training.shared.AggregateRoot;


public class AvailableOffer extends AggregateRoot<OfferId> {

    private final HostId host;
    private Seats availableSeats;
    private final Planning planning;

    public AvailableOffer(HostId host, OfferId offerId, Seats seats, Planning planning) {
        super(offerId);
        this.availableSeats = seats;
        this.host = host;
        this.planning = planning;
    }


    public Reservation book() {
        Reservation reservation = new Reservation(id, host);
        availableSeats = availableSeats.decrement();
        return reservation;
    }

    public boolean isApproved() {
        return false;
    }

    public void bookRefused() {
        availableSeats = availableSeats.increment();
    }

    public Seats getAvailableSeats() {
        return this.availableSeats;
    }

    public Planning getPlanning() {
        return this.planning;
    }
}
