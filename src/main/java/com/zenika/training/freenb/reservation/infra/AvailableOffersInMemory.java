package com.zenika.training.freenb.reservation.infra;

import com.zenika.training.freenb.reservation.application.SearchQuery;
import com.zenika.training.freenb.reservation.domain.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.OfferId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvailableOffersInMemory implements AvailableOffers {

    Map<OfferId, AvailableOffer> repo = new HashMap<>();

    @Override
    public List<AvailableOffer> search(SearchQuery searchQuery) {
        return new ArrayList<>(repo.values());
    }

    @Override
    public void add(AvailableOffer availableOffer) {
        repo.put(availableOffer.getOfferId(), availableOffer);
    }
}
