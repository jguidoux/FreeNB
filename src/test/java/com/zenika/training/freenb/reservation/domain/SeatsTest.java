package com.zenika.training.freenb.reservation.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SeatsTest {

    @Test
    void should_decrement() {
        Seats seats = Seats.fromInt(2);

        Seats decrementedSeats = seats.decrement();

        assertThat(seats).isEqualTo(Seats.fromInt(1));


    }

    @Test
    void all_seats_ere_occupied_when_value_is_0() {

        Seats seats = Seats.fromInt(0);

        boolean areAllOccupied = seats.areAllOccupied();

        assertThat(areAllOccupied).isTrue();

    }

    @Test
    void there_are_free_seats_when_seats_value_is_upper_to_0() {

        Seats seats = Seats.fromInt(1);

        boolean areAllOccupied = seats.areAllOccupied();

        assertThat(areAllOccupied).isFalse();

    }
}