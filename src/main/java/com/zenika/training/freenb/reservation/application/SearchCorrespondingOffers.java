package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.availableoffers.CorrespondingOffer;
import com.zenika.training.freenb.reservation.domain.availableoffers.SearchQuery;

import java.util.List;

public class SearchCorrespondingOffers {
    private final AvailableOffers repo;

    public SearchCorrespondingOffers(AvailableOffers repo) {
        this.repo = repo;
    }

    public List<CorrespondingOffer> execute(SearchQuery searchQuery) {
        return repo.search(searchQuery).stream()
                   .map(availableOffer -> new CorrespondingOffer(availableOffer.getId()))
                   .toList();
    }
}
