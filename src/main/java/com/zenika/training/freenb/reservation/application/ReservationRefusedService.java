package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.reservation.ReservationRefused;
import com.zenika.training.shared.domain_event.DomainEvent;

import java.util.Objects;

public class ReservationRefusedService {

    private final AvailableOffers availableOffers;

    public ReservationRefusedService(AvailableOffers availableOffers) {
        this.availableOffers = availableOffers;
    }

    public void execute(DomainEvent event) {
        if (Objects.requireNonNull(event) instanceof ReservationRefused refusedEvent) {
            AvailableOffer availableOffer = this.availableOffers.findById(refusedEvent.idOffer());
            availableOffer.bookRefused(refusedEvent.period());
            this.availableOffers.update(availableOffer);
        }
    }
}
