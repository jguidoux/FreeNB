package com.zenika.training.freenb.reservation.domain;

import com.zenika.training.freenb.reservation.application.Reservation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookAvailableOfferTest {

    @Test
    void should_book_a_reservation() {

        AvailableOffer availableOffer = new AvailableOffer(OfferId.create(), Seats.fromInt(50));

        Reservation book = availableOffer.book();

        assertThat(book.offerId()).isEqualTo(availableOffer.getId());
    }

    @Test
    void booking_should_decrement_available_seats() {

        AvailableOffer availableOffer = new AvailableOffer(OfferId.create(), Seats.fromInt(5));

        availableOffer.book();

        assertThat(availableOffer.getAvailableSeats()).isEqualTo(Seats.fromInt(4));
    }


}
