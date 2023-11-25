package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.reservation.application.AddNewAvailableOffer;
import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.availableoffers.OfferId;
import com.zenika.training.freenb.reservation.domain.availableoffers.SearchQuery;
import com.zenika.training.freenb.reservation.infra.AvailableOffersInMemory;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ReceivedOfferPublishedIT {

    @Test
    void should_insert_new_available_offer_from_published_offer() {
        String offerId = UUID.randomUUID().toString();
        String hostId = UUID.randomUUID().toString();
        OfferPublished offerPublished = new OfferPublished(hostId, offerId, 2);

        AvailableOffersInMemory repo = new AvailableOffersInMemory();
        AddNewAvailableOffer service = new AddNewAvailableOffer(repo);
        OfferPublishedConsumer offerPublishedConsumer = new OfferPublishedConsumer(service);

        offerPublishedConsumer.receive(offerPublished);

        List<AvailableOffer> foundOffers = repo.search(new SearchQuery());
        assertThat(foundOffers).hasSize(1);
        AvailableOffer offer = foundOffers.get(0);
        assertThat(offer.getId()).isEqualTo(new OfferId(offerId));


    }
}
