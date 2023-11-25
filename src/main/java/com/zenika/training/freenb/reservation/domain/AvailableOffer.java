package com.zenika.training.freenb.reservation.domain;

import com.zenika.training.freenb.publishing.domain.IdFreelanceHost;
import com.zenika.training.shared.AggregateRoot;
import lombok.Getter;


public class AvailableOffer extends AggregateRoot<OfferId> {

    @Getter
    private Seats availableSeats;
    private  final IdFreelanceHost host;

    public AvailableOffer(IdFreelanceHost host, OfferId offerId, Seats seats) {
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
