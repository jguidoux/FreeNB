package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.OfferId;

public class BookReservationService {
    private final AvailableOffers repo;

    public BookReservationService(AvailableOffers repo) {
        this.repo = repo;
    }

    public Reservation execute(OfferId id) {
        AvailableOffer availableOffer = repo.findById(id);
        return availableOffer.book();
    }
}
