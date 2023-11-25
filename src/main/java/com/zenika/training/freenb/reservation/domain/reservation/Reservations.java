package com.zenika.training.freenb.reservation.domain.reservation;

import com.zenika.training.freenb.reservation.domain.reservation.Reservation;
import com.zenika.training.freenb.reservation.domain.reservation.ReservationId;

public interface Reservations {
    Reservation findById(ReservationId id);

    void save(Reservation reservations);


}
