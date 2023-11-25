package com.zenika.training.freenb.reservation.domain;

import com.zenika.training.freenb.publishing.domain.IdFreelanceHost;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BookAvailableOfferTest {

    public static final IdFreelanceHost HOST = IdFreelanceHost.create();

    @Test
    void should_book_a_reservation() {

        AvailableOffer availableOffer = new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(50));

        Reservation book = availableOffer.book();

        assertThat(book.getOfferId()).isEqualTo(availableOffer.getId());
    }

    @Test
    void booking_should_decrement_available_seats() {

        AvailableOffer availableOffer = new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(5));

        availableOffer.book();

        assertThat(availableOffer.getAvailableSeats()).isEqualTo(Seats.fromInt(4));
    }

    @Test
    void new_reservation_status_should_not_be_in_approved_when_just_booked() {

        AvailableOffer availableOffer = new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(5));

        availableOffer.book();

        assertThat(availableOffer.isApproved()).isFalse();
    }

}
