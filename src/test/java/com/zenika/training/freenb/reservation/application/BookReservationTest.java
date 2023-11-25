package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.publishing.domain.FreelanceHostId;
import com.zenika.training.freenb.reservation.domain.*;
import com.zenika.training.freenb.reservation.infra.AvailableOffersInMemory;
import com.zenika.training.freenb.reservation.infra.ReservationsInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookReservationTest {

    public static final FreelanceHostId HOST = FreelanceHostId.create();
    private AvailableOffers availableOffers;
    private Reservations reservations;
    private BookReservationService service;

    @BeforeEach
    void setUp() {
        availableOffers = new AvailableOffersInMemory();
        reservations = new ReservationsInMemory();
        service = new BookReservationService(availableOffers, reservations);

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
        availableOffers.add(new AvailableOffer(HOST, offerId,  Seats.fromInt(availableSeats)));
        return offerId;
    }
}
