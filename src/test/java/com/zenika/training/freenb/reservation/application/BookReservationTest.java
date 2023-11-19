package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.OfferId;
import com.zenika.training.freenb.reservation.domain.Seats;
import com.zenika.training.freenb.reservation.infra.AvailableOffersInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BookReservationTest {

    private AvailableOffers availableOffers;

    @BeforeEach
    void setUp() {
        availableOffers = new AvailableOffersInMemory();

    }

    @Test
    void should_book_reservation_from_corresponding_offer() {

        OfferId availableOfferId = existAvailableOfferWith(5);
        BookReservationService service = new BookReservationService(availableOffers);

        Reservation reservation = service.execute(availableOfferId);

        assertThat(reservation.offerId()).isEqualTo(availableOfferId);
    }

    private OfferId existAvailableOfferWith(int availableSeats) {
        OfferId offerId = OfferId.create();
        availableOffers.add(new AvailableOffer(offerId,  Seats.fromInt(availableSeats)));
        return offerId;
    }
}
