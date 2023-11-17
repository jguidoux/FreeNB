package com.zenika.training.freenb.publishing.applcation;

import com.zenika.training.freenb.publishing.domain.*;
import com.zenika.training.freenb.reservation.api.OfferPublished;

public class PublishOfferService {
    private final Offers offerRepository;
    private final OfferPublisher publisher;

    public PublishOfferService(Offers offerRepository, OfferPublisher publisher) {

        this.offerRepository = offerRepository;
        this.publisher = publisher;
    }

    public IdOffer execute(IdWorkspace aWorkspaceId, OfferPeriod aPeriod) {
        Offer offer = new Offer(aWorkspaceId, aPeriod);
        offerRepository.publish(offer);
        publisher.publish(new OfferPublished(offer.getId().value()));
        return offer.getId();
    }
}
