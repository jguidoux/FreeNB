package com.zenika.training.freenb.reservation.domain.reservation;

import com.zenika.training.freenb.publishing.domain.FreelanceHostId;
import com.zenika.training.freenb.reservation.domain.availableoffers.OfferId;
import com.zenika.training.shared.AggregateRoot;
import lombok.Getter;


public class Reservation extends AggregateRoot<ReservationId> {

    @Getter
    private final OfferId offerId;

    @Getter
    private ReservationStatus status;
    @Getter
    private final FreelanceHostId host;


    public Reservation(OfferId offerId, FreelanceHostId host) {
        super(ReservationId.create());
        this.offerId = offerId;
        this.host = host;
        this.status = ReservationStatus.WAITING_ANSWER;
    }




    public void refused(FreelanceHostId refuser) {
        if (!refuser.equals(this.host)) {
            throw new NotAuthorizeToRefuseReservation();
        }
        this.status = ReservationStatus.REFUSED;
        ReservationRefused reservationRefused = new ReservationRefused(this.offerId);
        this.record(reservationRefused);
    }


}
