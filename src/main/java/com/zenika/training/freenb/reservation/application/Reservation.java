package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.OfferId;
import com.zenika.training.shared.AggregateRoot;

public class Reservation extends AggregateRoot<ReservationId> {
    private final OfferId offerId;


    public Reservation(OfferId offerId) {
        super(ReservationId.create());
        this.offerId = offerId;
    }

    public OfferId offerId() {
        return offerId;
    }
}
