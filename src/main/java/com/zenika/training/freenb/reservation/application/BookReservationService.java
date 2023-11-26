package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.availableoffers.CorrespondingOffer;
import com.zenika.training.freenb.reservation.domain.reservation.Reservation;
import com.zenika.training.freenb.reservation.domain.reservation.Reservations;

public class BookReservationService {
    private final AvailableOffers repo;

    private final Reservations reservations;

    public BookReservationService(AvailableOffers repo, Reservations reservations) {
        this.repo = repo;
        this.reservations = reservations;
    }

    public Reservation execute(CorrespondingOffer correspondingOffer) {
        AvailableOffer availableOffer = repo.findById(correspondingOffer.id());

        Reservation newReservation = availableOffer.book(correspondingOffer.period());
        reservations.save(newReservation);
        return newReservation;
    }
}
