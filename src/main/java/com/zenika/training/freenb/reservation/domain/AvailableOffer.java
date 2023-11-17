package com.zenika.training.freenb.reservation.domain;

import com.zenika.training.shared.AggregateRoot;

public class AvailableOffer extends AggregateRoot<OfferId> {
    public AvailableOffer(OfferId offerId) {
            super(offerId);
    }

}
