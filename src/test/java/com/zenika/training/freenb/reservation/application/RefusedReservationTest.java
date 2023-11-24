package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.*;
import com.zenika.training.freenb.reservation.infra.AvailableOffersInMemory;
import com.zenika.training.freenb.reservation.infra.ReservationsInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RefusedReservationTest {

    public static final int AVAILABLE_SEATS_AT_START = 5;
    private AvailableOffersInMemory availableOffers;
    private AddNewAvailableOffer addNewAvailableOffer;
    private Reservations reservations;
    private BookReservationService bookReservationService;
    private RefusedReservationService refusedReservationService;

    @BeforeEach
    void setUp() {
        availableOffers = new AvailableOffersInMemory();
        addNewAvailableOffer = new AddNewAvailableOffer(availableOffers);
        reservations = new ReservationsInMemory();
        bookReservationService = new BookReservationService(availableOffers, reservations);
        refusedReservationService = new RefusedReservationService(reservations, availableOffers);

    }

    @Test
    void new_reservation_should_not_be_in_status_waiting() {

        Reservation existingReservation = new Reservation(OfferId.create());

        assertThat(existingReservation.getStatus()).isEqualTo(ReservationStatus.WAITING_ANSWER);
    }

    @Test
    void refused_reservation_should_have_refused_status() {
        OfferId offerId = anAvailableOfferExist();
        Reservation existingReservation = bookReservationService.execute(offerId);

        refusedReservationService.execute(existingReservation.getId());

        Reservation reservation = reservations.findById(existingReservation.getId());
        assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.REFUSED);
    }

    @Test
    void refusing_reservation_should_increment_free_seat_number_for_the_offer() {

        OfferId offerId = anAvailableOfferExist();

        Reservation reservation = bookReservationService.execute(offerId);
        ReservationId reservationId = reservation.getId();
        RefusedReservationService refusedReservationService = new RefusedReservationService((reservations), availableOffers);

        refusedReservationService.execute(reservationId);

        Seats availableSeats = availableOffers.findById(offerId).getAvailableSeats();
        assertThat(availableSeats).isEqualTo(Seats.fromInt(AVAILABLE_SEATS_AT_START));
    }

    private OfferId anAvailableOfferExist() {
        OfferId offerId = OfferId.create();
        addNewAvailableOffer.execute(new AvailableOffer(offerId, Seats.fromInt(AVAILABLE_SEATS_AT_START)));
        return offerId;
    }


}
