package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.TestUtils;
import com.zenika.training.freenb.reservation.domain.HostId;
import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.availableoffers.CorrespondingOffer;
import com.zenika.training.freenb.reservation.domain.availableoffers.OfferId;
import com.zenika.training.freenb.reservation.domain.availableoffers.Seats;
import com.zenika.training.freenb.reservation.domain.reservation.*;
import com.zenika.training.freenb.reservation.infra.AvailableOffersInMemory;
import com.zenika.training.freenb.reservation.infra.ReservationsInMemory;
import com.zenika.training.shared.domain_event.DomainEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.zenika.training.freenb.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

class RefusedReservationTest {

    public static final int AVAILABLE_SEATS_AT_START = 5;
    public static final HostId HOST = HostId.create();
    private AvailableOffersInMemory availableOffers;
    private AddNewAvailableOffer addNewAvailableOffer;
    private Reservations reservations;
    private BookReservationService bookReservationService;

    @BeforeEach
    void setUp() {
        availableOffers = new AvailableOffersInMemory();
        addNewAvailableOffer = new AddNewAvailableOffer(availableOffers);
        reservations = new ReservationsInMemory();
        bookReservationService = new BookReservationService(availableOffers, reservations);

        DomainEventPublisher.register(evt -> new ReservationRefusedService(availableOffers).execute(evt), ReservationRefused.class.getCanonicalName());


    }

    @Test
    void new_reservation_should_not_be_in_status_waiting() {

        Reservation existingReservation = new Reservation(OfferId.create(), HOST, TWO_FIRST_DAYS_OF_NOVEMBER_PERIOD);

        assertThat(existingReservation.getStatus()).isEqualTo(ReservationStatus.WAITING_ANSWER);
    }


    @Test
    void refusing_reservation_should_increment_free_seat_number_for_the_offer() {

        OfferId offerId = anAvailableOfferExist();

        Reservation reservation = bookReservationService.execute(new CorrespondingOffer(offerId, TWO_FIRST_DAYS_OF_NOVEMBER_PERIOD));
        ReservationId reservationId = reservation.getId();
        RefuseReservationService refusedReservationService = new RefuseReservationService(reservations);

        RefuseReservationCommand refuseOfferRequest = new RefuseReservationCommand(reservationId, HOST);
        refusedReservationService.execute(refuseOfferRequest);

        AvailableOffer offer = availableOffers.findById(offerId);
        assertThat(offer.getAvailableSeatsForDay(NOV_1)).isEqualTo(Seats.fromInt(AVAILABLE_SEATS_AT_START));
        assertThat(offer.getAvailableSeatsForDay(NOV_2)).isEqualTo(Seats.fromInt(AVAILABLE_SEATS_AT_START));
    }

    @Test
    void only_host_should_be_able_to_refused_a_reservation() {

        OfferId offerId = anAvailableOfferExist();

        Reservation reservation = bookReservationService.execute(new CorrespondingOffer(offerId, TWO_FIRST_DAYS_OF_NOVEMBER_PERIOD));
        ReservationId reservationId = reservation.getId();
        RefuseReservationService refusedReservationService = new RefuseReservationService(reservations);

        RefuseReservationCommand refuseOfferRequest = new RefuseReservationCommand(reservationId, HOST);
        refusedReservationService.execute(refuseOfferRequest);

        AvailableOffer offer = availableOffers.findById(offerId);

        assertThat(offer.getAvailableSeatsForDay(NOV_1)).isEqualTo(Seats.fromInt(AVAILABLE_SEATS_AT_START));
        assertThat(offer.getAvailableSeatsForDay(NOV_2)).isEqualTo(Seats.fromInt(AVAILABLE_SEATS_AT_START));

    }

    private OfferId anAvailableOfferExist() {
        OfferId offerId = OfferId.create();
        addNewAvailableOffer.execute(new AvailableOffer(HOST, offerId, Seats.fromInt(AVAILABLE_SEATS_AT_START), TestUtils.TWO_FIRST_DAYS_OF_NOVEMBER));
        return offerId;
    }


}
