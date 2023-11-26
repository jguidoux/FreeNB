package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.HostId;
import com.zenika.training.freenb.reservation.domain.PeriodCriteria;
import com.zenika.training.freenb.reservation.domain.availableoffers.*;
import com.zenika.training.freenb.reservation.infra.AvailableOffersInMemory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SearchCorrespondingOfferTest {

    public static final HostId HOST = HostId.create();
    private Set<LocalDate> days;

    @BeforeEach
    void setUp() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        days = Set.of(day1, day2);
    }

    @Test
    void should_find_some_corresponding_offers() {

        AvailableOffersInMemory repo = new AvailableOffersInMemory();
        repo.add(new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(3), days));
        SearchCorrespondingOffers searchCorrespondingOffer = new SearchCorrespondingOffers(repo);

        LocalDate from = LocalDate.of(2023, 11, 1);
        LocalDate to = LocalDate.of(2023, 11, 2);
        PeriodCriteria period = PeriodCriteria.between(from, to);
        SearchQuery searchQuery = new SearchQuery(period);
        List<CorrespondingOffer> offers = searchCorrespondingOffer.execute(searchQuery);

        assertThat(offers).hasSize(1);

    }

    @Test
    void offer_with_no_free_seats_cant_be_found() {

        AvailableOffersInMemory repo = new AvailableOffersInMemory();
        repo.add(new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(0), days));
        SearchCorrespondingOffers searchCorrespondingOffer = new SearchCorrespondingOffers(repo);

        LocalDate from = LocalDate.of(2023, 11, 1);
        LocalDate to = LocalDate.of(2023, 11, 2);
        PeriodCriteria period = PeriodCriteria.between(from, to);
        SearchQuery searchQuery = new SearchQuery(period);
        List<CorrespondingOffer> offers = searchCorrespondingOffer.execute(searchQuery);

        assertThat(offers).isEmpty();

    }

    @Test
    void offers_with_availabilities_in_the_request_period_should_be_found() {

        AvailableOffersInMemory repo = new AvailableOffersInMemory();
        SearchCorrespondingOffers searchCorrespondingOffer = new SearchCorrespondingOffers(repo);
        repo.add(new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(3), days));

        LocalDate from = LocalDate.of(2023, 11, 1);
        LocalDate to = LocalDate.of(2023, 11, 2);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        SearchQuery searchQuery = new SearchQuery(period);
        List<CorrespondingOffer> offers = searchCorrespondingOffer.execute(searchQuery);

        assertThat(offers).hasSize(1);

    }

    @Test
    void offers_with_no_availabilities_in_the_request_period_should_not_be_found() {

        AvailableOffersInMemory repo = new AvailableOffersInMemory();
        SearchCorrespondingOffers searchCorrespondingOffer = new SearchCorrespondingOffers(repo);
        repo.add(new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(3), days));

        LocalDate from = LocalDate.of(2023, 12, 1);
        LocalDate to = LocalDate.of(2023, 12, 2);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        SearchQuery searchQuery = new SearchQuery(period);
        List<CorrespondingOffer> offers = searchCorrespondingOffer.execute(searchQuery);

        assertThat(offers).isEmpty();

    }
}
