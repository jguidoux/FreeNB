package com.zenika.training.freenb.reservation.infra;

import com.zenika.training.freenb.reservation.application.Reservation;
import com.zenika.training.freenb.reservation.application.ReservationId;
import com.zenika.training.freenb.reservation.domain.Reservations;

import java.util.HashMap;
import java.util.Map;

public class ReservationsInMemory implements Reservations {

    final Map<ReservationId, Reservation> map = new HashMap<>();
    @Override
    public Reservation findById(ReservationId id) {
        return map.get(id);
    }

    @Override
    public void save(Reservation newReservation) {
        map.put(newReservation.getId(), newReservation);
    }
}
