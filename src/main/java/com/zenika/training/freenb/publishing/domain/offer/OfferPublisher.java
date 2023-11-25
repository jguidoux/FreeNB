package com.zenika.training.freenb.publishing.domain.offer;

import com.zenika.training.freenb.reservation.api.OfferPublished;

public interface OfferPublisher {
    void publish(OfferPublished offerPublished);
}
