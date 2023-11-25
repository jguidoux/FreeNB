package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.*;

public class BookReservationService {
    private final AvailableOffers repo;

    private final Reservations reservations;

    public BookReservationService(AvailableOffers repo, Reservations reservations) {
        this.repo = repo;
        this.reservations = reservations;
    }

    public Reservation execute(OfferId id) {
        AvailableOffer availableOffer = repo.findById(id);
        Reservation newReservation = availableOffer.book();
        reservations.save(newReservation);
        return newReservation;
    }
}
