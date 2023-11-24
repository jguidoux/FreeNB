package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.OfferId;
import com.zenika.training.freenb.reservation.domain.ReservationStatus;
import com.zenika.training.shared.AggregateRoot;

public class Reservation extends AggregateRoot<ReservationId> {
    private final OfferId offerId;
    private ReservationStatus status;


    public Reservation(OfferId offerId) {
        super(ReservationId.create());
        this.offerId = offerId;
        this.status = ReservationStatus.WAITING_ANSWER;
    }

    public OfferId offerId() {
        return offerId;
    }


    public ReservationStatus getStatus() {
        return this.status;
    }

    public void refused() {
        this.status = ReservationStatus.REFUSED;
    }
}
