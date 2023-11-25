package com.zenika.training.freenb.reservation.domain.reservation;

public interface Reservations {
    Reservation findById(ReservationId id);

    void save(Reservation reservations);


}
