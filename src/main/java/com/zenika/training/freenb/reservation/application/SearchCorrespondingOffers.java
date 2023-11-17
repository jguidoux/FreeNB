package com.zenika.training.freenb.reservation.application;

import com.zenika.training.freenb.reservation.domain.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.CorrespondingOffer;

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
