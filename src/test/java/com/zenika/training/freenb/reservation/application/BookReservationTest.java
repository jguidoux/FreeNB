package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.HostId;
import com.zenika.training.freenb.reservation.domain.PeriodCriteria;
import com.zenika.training.freenb.reservation.domain.availableoffers.*;
import com.zenika.training.freenb.reservation.domain.reservation.Reservation;
import com.zenika.training.freenb.reservation.domain.reservation.Reservations;
import com.zenika.training.freenb.reservation.infra.AvailableOffersInMemory;
import com.zenika.training.freenb.reservation.infra.ReservationsInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class BookReservationTest {

    public static final HostId HOST = HostId.create();
    private AvailableOffers availableOffers;
    private Reservations reservations;
    private BookReservationService bookingService;

    private SearchCorrespondingOffers searchService;
    private Set<LocalDate> days;
    private Set<LocalDate> novemberDays;

    @BeforeEach
    void setUp() {
        availableOffers = new AvailableOffersInMemory();
        reservations = new ReservationsInMemory();
        bookingService = new BookReservationService(availableOffers, reservations);
        searchService = new SearchCorrespondingOffers(availableOffers);

        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        days = Set.of(day1, day2);

        novemberDays = LocalDate.of(2023, 11, 1).datesUntil(LocalDate.of(2023, 12, 1))
                                .collect(Collectors.toSet());

    }

    @Test
    void should_book_reservation_from_corresponding_offer() {

        OfferId availableOfferId = existAvailableOfferWith(5);

        PeriodCriteria period = getPeriodCriteria();
        List<CorrespondingOffer> offers = searchService.execute(new SearchQuery(period));
        CorrespondingOffer correspondingOffer = offers.get(0);

        Reservation reservation = bookingService.execute(correspondingOffer);

        assertThat(reservation.getOfferId()).isEqualTo(availableOfferId);
        assertThat(reservation.getHost()).isEqualTo(HOST);
    }

    @Test
    void new_reservation_should_be_saved() {

        existAvailableOfferWith(2);
        PeriodCriteria period = getPeriodCriteria();
        List<CorrespondingOffer> offers = searchService.execute(new SearchQuery(period));
        CorrespondingOffer correspondingOffer = offers.get(0);

        Reservation reservation = bookingService.execute(correspondingOffer);

        assertThat(reservations.findById(reservation.getId())).isNotNull();
    }

    @Test
    void should_no_possible_to_book_twice_same_period_when_1_seat_is_free() {

        existAvailableOfferWith(1);
        PeriodCriteria period = getPeriodCriteria();
        List<CorrespondingOffer> offers = searchService.execute(new SearchQuery(period));
        CorrespondingOffer correspondingOffer = offers.get(0);
        bookingService.execute(correspondingOffer);

        List<CorrespondingOffer> secondSearchResult = searchService.execute(new SearchQuery(period));

        assertThat(secondSearchResult).isEmpty();
    }

    @Test
    void should_be_possible_to_book_twice_same_offer_for_different_period_when_1_seat_is_free() {

        existAvailableOfferWith(novemberDays, 1);
        PeriodCriteria period = getPeriodCriteria();
        List<CorrespondingOffer> offers = searchService.execute(new SearchQuery(period));
        CorrespondingOffer correspondingOffer = offers.get(0);
        bookingService.execute(correspondingOffer);

        List<CorrespondingOffer> secondSearchResult = searchService.execute(new SearchQuery(getSecondPeriodCriteria()));

        assertThat(secondSearchResult).hasSize(1);
    }

    private OfferId existAvailableOfferWith(int availableSeats) {
        return existAvailableOfferWith(days, availableSeats);
    }

    private OfferId existAvailableOfferWith(Set<LocalDate> someDays, int availableSeats) {
        OfferId offerId = OfferId.create();
        availableOffers.add(new AvailableOffer(HOST, offerId, Seats.fromInt(availableSeats), someDays));
        return offerId;
    }

    private static PeriodCriteria getPeriodCriteria() {
        LocalDate from = LocalDate.of(2023, 11, 1);
        LocalDate to = LocalDate.of(2023, 11, 2);
        return PeriodCriteria.between(from, to);
    }

    private static PeriodCriteria getSecondPeriodCriteria() {
        LocalDate from = LocalDate.of(2023, 11, 10);
        LocalDate to = LocalDate.of(2023, 11, 15);
        return PeriodCriteria.between(from, to);
    }
}
