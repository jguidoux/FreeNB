package com.zenika.training.freenb.reservation.domain.availableoffers;

import java.util.List;

public interface AvailableOffers {
    List<AvailableOffer> search(SearchQuery searchQuery);

    void add(AvailableOffer newAvailableOffer);

    AvailableOffer findById(OfferId offerId);

    void update(AvailableOffer availableOffers);
}
