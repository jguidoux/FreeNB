package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.OfferId;
import com.zenika.training.freenb.reservation.domain.Seats;
import com.zenika.training.freenb.reservation.infra.AvailableOffersInMemory;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


public class NewOfferAvailableTest {


    @Test
    void should_add_new_available_offer() {

        AvailableOffers availableOffers = new AvailableOffersInMemory();
        AddNewAvailableOffer service =new AddNewAvailableOffer(availableOffers);
        AvailableOffer availableOffer = new AvailableOffer(new OfferId(UUID.randomUUID().toString()), new Seats(2));

        service.execute(availableOffer);

        List<AvailableOffer> foundOffers = availableOffers.search(new SearchQuery());

        assertThat(foundOffers).hasSize(1);
        AvailableOffer offer = foundOffers.get(0);
        assertThat(offer.getId()).isEqualTo(availableOffer.getId());
    }
}
