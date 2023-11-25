package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.HostId;
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
    private Planning planning;

    @BeforeEach
    void setUp() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        Set<LocalDate> days = Set.of(day1, day2);
        planning = Planning.fromListOfDays(days);
    }

    @Test
    void should_find_some_corresponding_offers() {

        AvailableOffersInMemory repo = new AvailableOffersInMemory();
        repo.add(new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(3), planning));
        SearchCorrespondingOffers searchCorrespondingOffer = new SearchCorrespondingOffers(repo);


        SearchQuery searchQuery = new SearchQuery();
        List<CorrespondingOffer> offers = searchCorrespondingOffer.execute(searchQuery);

        assertThat(offers).hasSize(1);

    }

    @Test
    void offer1_with_no_free_seats_cant_be_fins() {

        AvailableOffersInMemory repo = new AvailableOffersInMemory();
        repo.add(new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(0), planning));
        SearchCorrespondingOffers searchCorrespondingOffer = new SearchCorrespondingOffers(repo);


        SearchQuery searchQuery = new SearchQuery();
        List<CorrespondingOffer> offers = searchCorrespondingOffer.execute(searchQuery);

        assertThat(offers).isEmpty();

    }
}
