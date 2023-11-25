package com.zenika.training.freenb.reservation.domain;

import com.zenika.training.freenb.publishing.domain.IdFreelanceHost;
import com.zenika.training.shared.AggregateRoot;
import lombok.Getter;


public class Reservation extends AggregateRoot<ReservationId> {

    @Getter
    private final OfferId offerId;

    @Getter
    private ReservationStatus status;
    @Getter
    private final IdFreelanceHost host;


    public Reservation(OfferId offerId, IdFreelanceHost host) {
        super(ReservationId.create());
        this.offerId = offerId;
        this.host = host;
        this.status = ReservationStatus.WAITING_ANSWER;
    }




    public void refused(IdFreelanceHost refuser) {
        if (!refuser.equals(this.host)) {
            throw new NotAuthorizeToRefuseReservation();
        }
        this.status = ReservationStatus.REFUSED;
    }


}
