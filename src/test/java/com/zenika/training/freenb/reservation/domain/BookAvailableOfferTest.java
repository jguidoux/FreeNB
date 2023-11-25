package com.zenika.training.freenb.reservation.domain;

import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.availableoffers.OfferId;
import com.zenika.training.freenb.reservation.domain.availableoffers.Planning;
import com.zenika.training.freenb.reservation.domain.availableoffers.Seats;
import com.zenika.training.freenb.reservation.domain.reservation.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BookAvailableOfferTest {

    public static final HostId HOST = HostId.create();
    private Planning planning;

    @BeforeEach
    void setUp() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        Set<LocalDate> days = Set.of(day1, day2);
        planning = Planning.fromListOfDays(days);
    }


    @Test
    void should_book_a_reservation() {

        AvailableOffer availableOffer = new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(50), planning);

        Reservation book = availableOffer.book();

        assertThat(book.getOfferId()).isEqualTo(availableOffer.getId());
    }

    @Test
    void booking_should_decrement_available_seats() {

        AvailableOffer availableOffer = new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(5), planning);

        availableOffer.book();

        assertThat(availableOffer.getAvailableSeats()).isEqualTo(Seats.fromInt(4));
    }

    @Test
    void new_reservation_status_should_not_be_in_approved_when_just_booked() {

        AvailableOffer availableOffer = new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(5), planning);

        availableOffer.book();

        assertThat(availableOffer.isApproved()).isFalse();
    }

}
