package com.zenika.training.freenb.reservation.domain.reservation;

import com.zenika.training.freenb.reservation.domain.HostId;

public record RefuseReservationCommand(ReservationId reservationId, HostId host) {
}
