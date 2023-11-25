package com.zenika.training.freenb.reservation.domain;

import com.zenika.training.freenb.publishing.domain.IdFreelanceHost;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReservationTest {


    public static final IdFreelanceHost HOST = IdFreelanceHost.create();

    @Test
    void should_be_refused_when_refuser_is_the_owner() {
        Reservation reservation = new Reservation(OfferId.create(), HOST);

        reservation.refused(HOST);

        assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.REFUSED);
    }

    @Test
    void should_not_accept_when_refuser_is_not_the_owner() {
        Reservation reservation = new Reservation(OfferId.create(), HOST);

        assertThatThrownBy(() -> reservation.refused(IdFreelanceHost.create()))
                .isInstanceOf(NotAuthorizeToRefuseReservation.class);
    }
}