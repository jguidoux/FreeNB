package com.zenika.training.freenb.reservation.domain;

import com.zenika.training.freenb.reservation.application.Reservation;
import com.zenika.training.freenb.reservation.application.ReservationId;

public interface Reservations {
    Reservation findById(ReservationId id);

    void save(Reservation reservations);


}
