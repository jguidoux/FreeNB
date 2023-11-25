package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.reservation.RefuseReservationCommand;
import com.zenika.training.freenb.reservation.domain.reservation.Reservation;
import com.zenika.training.freenb.reservation.domain.reservation.Reservations;
import com.zenika.training.shared.domain_event.DomainEventPublisher;

public class RefuseReservationService {

    private final Reservations reservations;

    public RefuseReservationService(Reservations reservations) {
        this.reservations = reservations;
    }

    public void execute(RefuseReservationCommand request) {
        Reservation reservation = reservations.findById(request.reservationId());

        reservation.refused(request.host());
        this.reservations.save(reservation);
        reservation.pullDomainEvents()
                   .forEach(DomainEventPublisher::dispatch);

    }

}
