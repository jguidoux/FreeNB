package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.*;

public class RefusedReservationService {

    private final Reservations reservations;

    private final AvailableOffers availableOffers;

    public RefusedReservationService(Reservations reservations, AvailableOffers availableOffers) {
        this.reservations = reservations;
        this.availableOffers = availableOffers;
    }

    public void execute(RefuseReservationCommand request) {
        Reservation reservation = reservations.findById(request.reservationId());

        reservation.refused(request.host());
        this.reservations.save(reservation);


        AvailableOffer availableOffer = availableOffers.findById(reservation.getOfferId());
        availableOffer.bookRefused();

        availableOffers.update(availableOffer);
    }
}
