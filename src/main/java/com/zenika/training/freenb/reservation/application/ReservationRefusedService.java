package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.ReservationRefused;
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
            availableOffer.bookRefused();
            this.availableOffers.update(availableOffer);
        }
    }
}
