package com.zenika.training.freenb.reservation.infra;

import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.availableoffers.OfferId;
import com.zenika.training.freenb.reservation.domain.availableoffers.SearchQuery;
import com.zenika.training.freenb.reservation.domain.reservation.PeriodCriteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AvailableOffersInMemory implements AvailableOffers {

    private final Map<OfferId, AvailableOffer> repo = new HashMap<>();

    /**
     *
     */
    @Override
    public List<AvailableOffer> search(SearchQuery searchQuery) {
        PeriodCriteria period = searchQuery.period();
        return new ArrayList<>(repo.values()).stream()
                                             .filter(offer -> offer.hasFreeSeatsFor(searchQuery.period()))
                                             .filter(availableOffer -> availableOffer.containPeriod(period))
                                             .toList();
    }

    @Override
    public void add(AvailableOffer availableOffer) {
        repo.put(availableOffer.getId(), availableOffer);
    }

    @Override
    public AvailableOffer findById(OfferId offerId) {
        return repo.get(offerId);
    }

    @Override
    public void update(AvailableOffer availableOffer) {
        repo.put(availableOffer.getId(), availableOffer);
    }
}
