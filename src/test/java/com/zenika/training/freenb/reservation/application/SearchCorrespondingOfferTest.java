package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.publishing.domain.FreelanceHostId;
import com.zenika.training.freenb.reservation.domain.*;
import com.zenika.training.freenb.reservation.infra.AvailableOffersInMemory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SearchCorrespondingOfferTest {

    public static final FreelanceHostId HOST = FreelanceHostId.create();

    @Test
    void should_find_some_corresponding_offers() {

        AvailableOffersInMemory repo = new AvailableOffersInMemory();
        repo.add(new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(3)));
        SearchCorrespondingOffers searchCorrespondingOffer = new SearchCorrespondingOffers(repo);


        SearchQuery searchQuery = new SearchQuery();
        List<CorrespondingOffer> offers = searchCorrespondingOffer.execute(searchQuery);

        assertThat(offers).hasSize(1);

    }

    @Test
    void offer1_with_no_free_seats_cant_be_fins() {

        AvailableOffersInMemory repo = new AvailableOffersInMemory();
        repo.add(new AvailableOffer(HOST, OfferId.create(), Seats.fromInt(0)));
        SearchCorrespondingOffers searchCorrespondingOffer = new SearchCorrespondingOffers(repo);


        SearchQuery searchQuery = new SearchQuery();
        List<CorrespondingOffer> offers = searchCorrespondingOffer.execute(searchQuery);

        assertThat(offers).isEmpty();

    }
}
