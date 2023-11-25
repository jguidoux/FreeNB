package com.zenika.training.freenb.reservation.domain.availableoffers;

import com.zenika.training.freenb.reservation.domain.HostId;
import com.zenika.training.freenb.reservation.domain.reservation.Reservation;
import com.zenika.training.shared.AggregateRoot;
import lombok.Getter;


public class AvailableOffer extends AggregateRoot<OfferId> {

    @Getter
    private Seats availableSeats;
    private final HostId host;

    public AvailableOffer(HostId host, OfferId offerId, Seats seats) {
        super(offerId);
        this.availableSeats = seats;
        this.host = host;
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
}
