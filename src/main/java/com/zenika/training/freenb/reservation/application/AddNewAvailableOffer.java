package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffers;

public class AddNewAvailableOffer {
    private final AvailableOffers availableOffers;

    public AddNewAvailableOffer(AvailableOffers availableOffers) {

        this.availableOffers = availableOffers;
    }

    public void execute(AvailableOffer newAvailableOffer) {
        availableOffers.add(newAvailableOffer);
    }
}
