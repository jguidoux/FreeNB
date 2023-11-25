package com.zenika.training.freenb.reservation.domain.reservation;

import com.zenika.training.freenb.publishing.domain.FreelanceHostId;

public record RefuseReservationCommand(ReservationId reservationId, FreelanceHostId host) {
}
