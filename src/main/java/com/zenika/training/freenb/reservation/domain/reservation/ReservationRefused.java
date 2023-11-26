package com.zenika.training.freenb.reservation.domain.reservation;

import com.zenika.training.freenb.reservation.domain.availableoffers.OfferId;
import com.zenika.training.shared.domain_event.DomainEvent;

public record ReservationRefused(OfferId idOffer, PeriodCriteria period) implements DomainEvent {

}
