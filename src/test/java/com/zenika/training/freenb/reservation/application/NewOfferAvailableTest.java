package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.HostId;
import com.zenika.training.freenb.reservation.domain.availableoffers.*;
import com.zenika.training.freenb.reservation.domain.reservation.PeriodCriteria;
import com.zenika.training.freenb.reservation.infra.AvailableOffersInMemory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


public class NewOfferAvailableTest {


    public static final HostId HOST = HostId.create();

    @Test
    void should_add_new_available_offer() {

        AvailableOffers availableOffers = new AvailableOffersInMemory();
        AddNewAvailableOffer addOfferService = new AddNewAvailableOffer(availableOffers);
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        Set<LocalDate> days = Set.of(day1, day2);
        AvailableOffer availableOffer = new AvailableOffer(HOST,
                new OfferId(UUID.randomUUID().toString()),
                new Seats(2),
                days);

        addOfferService.execute(availableOffer);

        LocalDate from = LocalDate.of(2023, 11, 1);
        LocalDate to = LocalDate.of(2023, 11, 2);
        PeriodCriteria period = PeriodCriteria.between(from, to);
        List<AvailableOffer> foundOffers = availableOffers.search(new SearchQuery(period));

        assertThat(foundOffers).hasSize(1);
        AvailableOffer offer = foundOffers.get(0);
        assertThat(offer.getId()).isEqualTo(availableOffer.getId());
    }
}
