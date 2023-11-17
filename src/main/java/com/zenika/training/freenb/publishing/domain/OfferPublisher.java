package com.zenika.training.freenb.publishing.domain;

import com.zenika.training.freenb.reservation.api.OfferPublished;

public interface OfferPublisher {
    void publish(OfferPublished offerPublished);
}
