package com.zenika.training.freenb.reservation.domain.reservation;

import com.zenika.training.freenb.reservation.domain.HostId;
import com.zenika.training.freenb.reservation.domain.PeriodCriteria;
import com.zenika.training.freenb.reservation.domain.availableoffers.OfferId;
import com.zenika.training.shared.AggregateRoot;


public class Reservation extends AggregateRoot<ReservationId> {

    private final OfferId offerId;
    private final HostId host;
    private ReservationStatus status;
    private final PeriodCriteria period;


    public Reservation(OfferId offerId, HostId host, PeriodCriteria period) {
        super(ReservationId.create());
        this.offerId = offerId;
        this.host = host;
        this.period = period;
        this.status = ReservationStatus.WAITING_ANSWER;
    }


    public void refused(HostId refuser) {
        if (!refuser.equals(this.host)) {
            throw new NotAuthorizeToRefuseReservation();
        }
        this.status = ReservationStatus.REFUSED;
        ReservationRefused reservationRefused = new ReservationRefused(this.offerId, this.period);
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
