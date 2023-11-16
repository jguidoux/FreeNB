package com.zenika.training.freenb.publishing.applcation;

import com.zenika.training.freenb.publishing.domain.*;

public class PublishOfferService {
    private final Offers offerRepository;

    public PublishOfferService(Offers offerRepository) {

        this.offerRepository = offerRepository;
    }

    public IdOffer execute(IdWorkspace aWorkspaceId, OfferPeriod aPeriod) {
        Offer offer = new Offer(aWorkspaceId, aPeriod);
        offerRepository.publish(offer);
        return offer.getId();
    }
}
