package com.zenika.training.freenb.reservation.domain;

import com.zenika.training.freenb.reservation.domain.availableoffers.SeatNumberShouldBePositive;
import com.zenika.training.freenb.reservation.domain.availableoffers.Seats;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SeatsTest {

    @Test
    void seat_number_should_be_positive() {

        assertThatThrownBy(() -> Seats.fromInt(-1))
                .isInstanceOf(SeatNumberShouldBePositive.class);


    }

    @Test
    void should_decrement() {
        Seats seats = Seats.fromInt(2);

        Seats decrementedSeats = seats.decrement();

        assertThat(decrementedSeats).isEqualTo(Seats.fromInt(1));


    }

    @Test
    void should_be_never_lower_to_0_when_decrement() {
        Seats seats = Seats.fromInt(0);

        Seats decrementedSeats = seats.decrement();

        assertThat(decrementedSeats).isEqualTo(Seats.fromInt(0));


    }

    @Test
    void all_seats_ere_occupied_when_value_is_0() {

        Seats seats = Seats.fromInt(0);

        boolean areAllOccupied = seats.haveFreePlaces();

        assertThat(areAllOccupied).isFalse();

    }

    @Test
    void there_are_free_seats_when_seats_value_is_upper_to_0() {

        Seats seats = Seats.fromInt(1);

        boolean areAllOccupied = seats.haveFreePlaces();

        assertThat(areAllOccupied).isTrue();

    }


    @Test
    void should_increment() {

        Seats seats = Seats.fromInt(1);

        Seats incremented = seats.increment();

        assertThat(incremented).isEqualTo(Seats.fromInt(2));
    }
}