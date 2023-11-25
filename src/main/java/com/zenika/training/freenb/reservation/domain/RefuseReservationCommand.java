package com.zenika.training.freenb.reservation.domain;

import com.zenika.training.freenb.publishing.domain.IdFreelanceHost;

public record RefuseReservationCommand(ReservationId reservationId, IdFreelanceHost host) {
}
