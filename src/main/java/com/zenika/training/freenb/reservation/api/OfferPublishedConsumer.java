package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.reservation.application.AddNewAvailableOffer;
import com.zenika.training.freenb.reservation.domain.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.OfferId;
import com.zenika.training.freenb.reservation.domain.Seats;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class OfferPublishedConsumer {

    private final AddNewAvailableOffer service;

    public OfferPublishedConsumer(AddNewAvailableOffer service) {
        this.service = service;
    }

    @EventListener
    public void receive(OfferPublished offerPublished) {
        AvailableOffer availableOffers = new AvailableOffer(new OfferId(offerPublished.value()), new Seats(offerPublished.capacity()));
        service.execute(availableOffers);
    }
}
