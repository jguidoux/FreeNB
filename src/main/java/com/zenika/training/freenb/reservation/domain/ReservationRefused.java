package com.zenika.training.freenb.reservation.domain;

import com.zenika.training.shared.domain_event.DomainEvent;

public record ReservationRefused(OfferId idOffer) implements DomainEvent {
}
