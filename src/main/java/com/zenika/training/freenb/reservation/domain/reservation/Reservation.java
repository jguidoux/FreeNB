package com.zenika.training.freenb.reservation.domain.reservation;

import com.zenika.training.freenb.reservation.domain.HostId;
import com.zenika.training.freenb.reservation.domain.availableoffers.OfferId;
import com.zenika.training.shared.AggregateRoot;


public class Reservation extends AggregateRoot<ReservationId> {

    private final OfferId offerId;
    private final HostId host;
    private ReservationStatus status;


    public Reservation(OfferId offerId, HostId host) {
        super(ReservationId.create());
        this.offerId = offerId;
        this.host = host;
        this.status = ReservationStatus.WAITING_ANSWER;
    }


    public void refused(HostId refuser) {
        if (!refuser.equals(this.host)) {
            throw new NotAuthorizeToRefuseReservation();
        }
        this.status = ReservationStatus.REFUSED;
        ReservationRefused reservationRefused = new ReservationRefused(this.offerId);
        this.record(reservationRefused);
    }


    public OfferId getOfferId() {
        return this.offerId;
    }

    public ReservationStatus getStatus() {
        return this.status;
    }

    public HostId getHost() {
        return this.host;
    }
}
