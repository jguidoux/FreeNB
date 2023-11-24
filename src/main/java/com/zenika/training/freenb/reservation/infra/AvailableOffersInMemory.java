package com.zenika.training.freenb.reservation.infra;

import com.zenika.training.freenb.reservation.application.SearchQuery;
import com.zenika.training.freenb.reservation.domain.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.OfferId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AvailableOffersInMemory implements AvailableOffers {

    private final Map<OfferId, AvailableOffer> repo = new HashMap<>();

    @Override
    public List<AvailableOffer> search(SearchQuery searchQuery) {
        return new ArrayList<>(repo.values()).stream()
                                             .filter(offer -> offer.getAvailableSeats().haveFreePlaces())
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
}
