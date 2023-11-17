package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.infra.AvailableOffersInMemory;
import org.junit.jupiter.api.Test;

public class SearchCorrespondingOfferTest {

    @Test
    void should_find_some_corresponding_offers() {

        SearchCorrespondingOffers searchCorrespondingOffer = new SearchCorrespondingOffers(new AvailableOffersInMemory());

        searchCorrespondingOffer.execute(new SearchQuery());
    }
}
