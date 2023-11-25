package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.*;

public class RefuseReservationService {

    private final Reservations reservations;

    private final ReservationRefusedService reservationRefusedService;

    public RefuseReservationService(Reservations reservations, ReservationRefusedService reservationRefusedService) {
        this.reservations = reservations;
        this.reservationRefusedService = reservationRefusedService;
    }

    public void execute(RefuseReservationCommand request) {
        Reservation reservation = reservations.findById(request.reservationId());

        reservation.refused(request.host());
        this.reservations.save(reservation);


        reservationRefusedService.reservationRefused(reservation);
    }

}
