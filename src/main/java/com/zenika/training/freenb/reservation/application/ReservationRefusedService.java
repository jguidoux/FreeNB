package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.Reservation;

public class ReservationRefusedService {

    private final AvailableOffers availableOffers;

    public ReservationRefusedService(AvailableOffers availableOffers) {
        this.availableOffers = availableOffers;
    }

    void reservationRefused(Reservation reservation) {
        AvailableOffer availableOffer = this.availableOffers.findById(reservation.getOfferId());
        availableOffer.bookRefused();
        this.availableOffers.update(availableOffer);
    }
}
