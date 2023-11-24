package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.CorrespondingOffer;
import com.zenika.training.freenb.reservation.domain.OfferId;
import com.zenika.training.freenb.reservation.domain.Seats;
import com.zenika.training.freenb.reservation.infra.AvailableOffersInMemory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SearchCorrespondingOfferTest {

    @Test
    void should_find_some_corresponding_offers() {

        AvailableOffersInMemory repo = new AvailableOffersInMemory();
        repo.add(new AvailableOffer(OfferId.create(), Seats.fromInt(3)));
        SearchCorrespondingOffers searchCorrespondingOffer = new SearchCorrespondingOffers(repo);


        SearchQuery searchQuery = new SearchQuery();
        List<CorrespondingOffer> offers = searchCorrespondingOffer.execute(searchQuery);

        assertThat(offers).hasSize(1);

    }

    @Test
    void offer1_with_no_free_seats_cant_be_fins() {

        AvailableOffersInMemory repo = new AvailableOffersInMemory();
        repo.add(new AvailableOffer(OfferId.create(), Seats.fromInt(0)));
        SearchCorrespondingOffers searchCorrespondingOffer = new SearchCorrespondingOffers(repo);


        SearchQuery searchQuery = new SearchQuery();
        List<CorrespondingOffer> offers = searchCorrespondingOffer.execute(searchQuery);

        assertThat(offers).isEmpty();

    }
}
