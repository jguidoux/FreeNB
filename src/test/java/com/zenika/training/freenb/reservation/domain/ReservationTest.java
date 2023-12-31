package com.zenika.training.freenb.reservation.domain;

import com.zenika.training.freenb.reservation.domain.availableoffers.OfferId;
import com.zenika.training.freenb.reservation.domain.reservation.NotAuthorizeToRefuseReservation;
import com.zenika.training.freenb.reservation.domain.reservation.Reservation;
import com.zenika.training.freenb.reservation.domain.reservation.ReservationStatus;
import org.junit.jupiter.api.Test;

import static com.zenika.training.freenb.TestUtils.TWO_FIRST_DAYS_OF_NOVEMBER_PERIOD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReservationTest {


    public static final HostId HOST = HostId.create();
    public static final HostId WRONG_HOST = HostId.create();

    @Test
    void should_be_refused_when_refuser_is_the_owner() {

        Reservation reservation = new Reservation(OfferId.create(), HOST, TWO_FIRST_DAYS_OF_NOVEMBER_PERIOD);

        reservation.refused(HOST);

        assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.REFUSED);
    }

    @Test
    void should_not_accept_when_refuser_is_not_the_owner() {

        Reservation reservation = new Reservation(OfferId.create(), HOST, TWO_FIRST_DAYS_OF_NOVEMBER_PERIOD);

        assertThatThrownBy(() -> reservation.refused(WRONG_HOST))
                .isInstanceOf(NotAuthorizeToRefuseReservation.class);
    }
}