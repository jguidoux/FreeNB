package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.reservation.application.AddNewAvailableOffer;
import com.zenika.training.freenb.reservation.domain.HostId;
import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.availableoffers.OfferId;
import com.zenika.training.freenb.reservation.domain.availableoffers.Seats;
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
        AvailableOffer availableOffers = new AvailableOffer(HostId.fromString(offerPublished.hostId()),
                new OfferId(offerPublished.value()),
                new Seats(offerPublished.capacity()),
                offerPublished.planning());
        service.execute(availableOffers);
    }
}
