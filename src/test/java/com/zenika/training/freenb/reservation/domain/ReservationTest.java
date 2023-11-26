package com.zenika.training.freenb.reservation.domain;

import com.zenika.training.freenb.reservation.domain.availableoffers.OfferId;
import com.zenika.training.freenb.reservation.domain.reservation.NotAuthorizeToRefuseReservation;
import com.zenika.training.freenb.reservation.domain.reservation.PeriodCriteria;
import com.zenika.training.freenb.reservation.domain.reservation.Reservation;
import com.zenika.training.freenb.reservation.domain.reservation.ReservationStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReservationTest {


    public static final HostId HOST = HostId.create();
    public static final HostId WRONG_HOST = HostId.create();

    @Test
    void should_be_refused_when_refuser_is_the_owner() {
        LocalDate from = LocalDate.of(2023, 11, 1);
        LocalDate to = LocalDate.of(2023, 11, 2);
        PeriodCriteria period = PeriodCriteria.between(from, to);
        Reservation reservation = new Reservation(OfferId.create(), HOST, period);

        reservation.refused(HOST);

        assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.REFUSED);
    }

    @Test
    void should_not_accept_when_refuser_is_not_the_owner() {
        LocalDate from = LocalDate.of(2023, 11, 1);
        LocalDate to = LocalDate.of(2023, 11, 2);
        PeriodCriteria period = PeriodCriteria.between(from, to);
        Reservation reservation = new Reservation(OfferId.create(), HOST, period);

        assertThatThrownBy(() -> reservation.refused(WRONG_HOST))
                .isInstanceOf(NotAuthorizeToRefuseReservation.class);
    }
}