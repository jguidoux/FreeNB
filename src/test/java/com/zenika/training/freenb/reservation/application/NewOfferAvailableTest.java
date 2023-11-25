package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.publishing.domain.IdFreelanceHost;
import com.zenika.training.freenb.reservation.domain.*;
import com.zenika.training.freenb.reservation.infra.AvailableOffersInMemory;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


public class NewOfferAvailableTest {


    public static final IdFreelanceHost HOST = IdFreelanceHost.create();

    @Test
    void should_add_new_available_offer() {

        AvailableOffers availableOffers = new AvailableOffersInMemory();
        AddNewAvailableOffer addOfferService =new AddNewAvailableOffer(availableOffers);
        AvailableOffer availableOffer = new AvailableOffer(HOST, new OfferId(UUID.randomUUID().toString()), new Seats(2));

        addOfferService.execute(availableOffer);

        List<AvailableOffer> foundOffers = availableOffers.search(new SearchQuery());

        assertThat(foundOffers).hasSize(1);
        AvailableOffer offer = foundOffers.get(0);
        assertThat(offer.getId()).isEqualTo(availableOffer.getId());
    }
}
