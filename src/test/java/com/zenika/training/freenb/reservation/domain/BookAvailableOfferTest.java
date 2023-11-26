package com.zenika.training.freenb.reservation.domain;

import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.availableoffers.OfferId;
import com.zenika.training.freenb.reservation.domain.availableoffers.Seats;
import com.zenika.training.freenb.reservation.domain.reservation.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BookAvailableOfferTest {

    public static final HostId HOST = HostId.create();
    private Set<LocalDate> days;

    @BeforeEach
    void setUp() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        LocalDate day3 = LocalDate.of(2023, 11, 3);
        LocalDate day4 = LocalDate.of(2023, 11, 4);
        LocalDate day5 = LocalDate.of(2023, 11, 5);
        days = Set.of(day1, day2, day3, day4, day5);
    }


    @Test
    void should_book_a_reservation() {

        AvailableOffer availableOffer = new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(50), days);

        LocalDate from = LocalDate.of(2023, 11, 3);
        LocalDate to = LocalDate.of(2023, 11, 4);
        PeriodCriteria period = PeriodCriteria.between(from, to);
        Reservation book = availableOffer.book(period);

        assertThat(book.getOfferId()).isEqualTo(availableOffer.getId());
    }

    @Test
    void should_decrease_available_seat_for_period() {

        AvailableOffer availableOffer = new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(1), days);

        LocalDate from = LocalDate.of(2023, 11, 3);
        LocalDate to = LocalDate.of(2023, 11, 4);
        PeriodCriteria period = PeriodCriteria.between(from, to);
        Reservation book = availableOffer.book(period);

        Seats freeSeatFor3Nov = availableOffer.getAvailableSeatsForDay(LocalDate.of(2023, 11, 3));
        Seats freeSeatFor4Nov = availableOffer.getAvailableSeatsForDay(LocalDate.of(2023, 11, 4));

        assertThat(freeSeatFor3Nov).isEqualTo(Seats.notFree());
        assertThat(freeSeatFor4Nov).isEqualTo(Seats.notFree());

        assertThat(book.getOfferId()).isEqualTo(availableOffer.getId());
    }


    @Test
    void booking_should_decrement_available_seats() {

        AvailableOffer availableOffer = new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(5), days);

        LocalDate from = LocalDate.of(2023, 11, 3);
        LocalDate to = LocalDate.of(2023, 11, 4);
        PeriodCriteria period = PeriodCriteria.between(from, to);
        availableOffer.book(period);

        assertThat(availableOffer.getAvailableSeatsForDay(from)).isEqualTo(Seats.fromInt(4));
        assertThat(availableOffer.getAvailableSeatsForDay(to)).isEqualTo(Seats.fromInt(4));
    }

    @Test
    void new_reservation_status_should_not_be_in_approved_when_just_booked() {

        AvailableOffer availableOffer = new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(5), days);

        LocalDate from = LocalDate.of(2023, 11, 3);
        LocalDate to = LocalDate.of(2023, 11, 4);
        PeriodCriteria period = PeriodCriteria.between(from, to);
        availableOffer.book(period);

        assertThat(availableOffer.isApproved()).isFalse();
    }


}
