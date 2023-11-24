package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.Reservations;

public class RefusedReservationService {

    private final Reservations reservations;

    private final AvailableOffers availableOffers;

    public RefusedReservationService(Reservations reservations, AvailableOffers availableOffers) {
        this.reservations = reservations;
        this.availableOffers = availableOffers;
    }

    public void execute(ReservationId id) {
        Reservation reservation = reservations.findById(id);

        reservation.refused();
        this.reservations.save(reservation);


        AvailableOffer availableOffer = availableOffers.findById(reservation.offerId());
        availableOffer.bookRefused();

        availableOffers.update(availableOffer);
    }
}
