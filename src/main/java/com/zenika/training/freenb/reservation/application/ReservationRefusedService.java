package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.ReservationRefused;

public class ReservationRefusedService {

    private final AvailableOffers availableOffers;

    public ReservationRefusedService(AvailableOffers availableOffers) {
        this.availableOffers = availableOffers;
    }

    void reservationRefused(ReservationRefused event) {
        AvailableOffer availableOffer = this.availableOffers.findById(event.idOffer());
        availableOffer.bookRefused();
        this.availableOffers.update(availableOffer);
    }
}
