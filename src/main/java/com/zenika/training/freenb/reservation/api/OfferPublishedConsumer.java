package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.reservation.application.AddNewAvailableOffer;
import com.zenika.training.freenb.reservation.domain.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.OfferId;
import org.springframework.stereotype.Service;

@Service
public class OfferPublishedConsumer {
    private final AddNewAvailableOffer service;

    public OfferPublishedConsumer(AddNewAvailableOffer service) {
        this.service = service;
    }

    public void receive(OfferPublished offerPublished) {
        AvailableOffer availableOffers = new AvailableOffer(new OfferId(offerPublished.value()));
        service.execute(availableOffers);
    }
}
