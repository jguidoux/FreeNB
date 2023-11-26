package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.reservation.application.AddNewAvailableOffer;
import com.zenika.training.freenb.reservation.domain.availableoffers.*;
import com.zenika.training.freenb.reservation.domain.reservation.PeriodCriteria;
import com.zenika.training.freenb.reservation.infra.AvailableOffersInMemory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.zenika.training.freenb.TestUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

class ReceivedOfferPublishedIT {

    public static final int CAPACITY_OF_2 = 2;

    @Test
    void should_insert_new_available_offer_from_published_offer() {
        AvailableOffersInMemory repo = new AvailableOffersInMemory();
        AddNewAvailableOffer service = new AddNewAvailableOffer(repo);
        OfferPublishedConsumer offerPublishedConsumer = new OfferPublishedConsumer(service);

        OfferPublished offerPublished = anOfferIsPublished();

        offerPublishedConsumer.receive(offerPublished);

        PeriodCriteria period = PeriodCriteria.between(NOV_1, NOV_2);
        List<AvailableOffer> foundOffers = repo.search(new SearchQuery(period));
        assertThat(foundOffers).hasSize(1);

        AvailableOffer offer = foundOffers.get(0);
        assertThat(offer.getId()).isEqualTo(new OfferId(offerPublished.offerId()));
        assertThat(offer.getPlanning()).isEqualTo(Planning.fromListOfDays(TWO_FIRST_DAYS_OF_NOVEMBER, Seats.fromInt(CAPACITY_OF_2)));


    }

    private static OfferPublished anOfferIsPublished() {
        String offerId = UUID.randomUUID().toString();
        String hostId = UUID.randomUUID().toString();
        Set<LocalDate> days = Set.of(NOV_1, NOV_2);
        return new OfferPublished(hostId, offerId, CAPACITY_OF_2, days);
    }
}
