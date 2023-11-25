package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.HostId;
import com.zenika.training.freenb.reservation.domain.availableoffers.*;
import com.zenika.training.freenb.reservation.domain.reservation.Reservation;
import com.zenika.training.freenb.reservation.domain.reservation.Reservations;
import com.zenika.training.freenb.reservation.infra.AvailableOffersInMemory;
import com.zenika.training.freenb.reservation.infra.ReservationsInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BookReservationTest {

    public static final HostId HOST = HostId.create();
    private AvailableOffers availableOffers;
    private Reservations reservations;
    private BookReservationService service;
    private Planning planning;

    @BeforeEach
    void setUp() {
        availableOffers = new AvailableOffersInMemory();
        reservations = new ReservationsInMemory();
        service = new BookReservationService(availableOffers, reservations);
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        Set<LocalDate> days = Set.of(day1, day2);
        planning = Planning.fromListOfDays(days);

    }

    @Test
    void should_book_reservation_from_corresponding_offer() {

        OfferId availableOfferId = existAvailableOfferWith(5);

        Reservation reservation = service.execute(availableOfferId);

        assertThat(reservation.getOfferId()).isEqualTo(availableOfferId);
        assertThat(reservation.getHost()).isEqualTo(HOST);
    }

    @Test
    void new_reservation_should_be_saved() {

        OfferId availableOfferId = existAvailableOfferWith(2);

        Reservation reservation = service.execute(availableOfferId);

        assertThat(reservations.findById(reservation.getId())).isNotNull();
    }

    private OfferId existAvailableOfferWith(int availableSeats) {
        OfferId offerId = OfferId.create();
        availableOffers.add(new AvailableOffer(HOST, offerId, Seats.fromInt(availableSeats), planning));
        return offerId;
    }
}
