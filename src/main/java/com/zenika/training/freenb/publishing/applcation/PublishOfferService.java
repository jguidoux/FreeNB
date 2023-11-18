package com.zenika.training.freenb.publishing.applcation;

import com.zenika.training.freenb.publishing.domain.*;
import com.zenika.training.freenb.reservation.api.OfferPublished;

import java.util.List;

public class PublishOfferService {
    private final Offers offerRepository;
    private final OfferPublisher publisher;
    private final CheckWorkspaceRequirements checkWorkspaceRequirements;

    public PublishOfferService(Offers offerRepository, OfferPublisher publisher, CheckWorkspaceRequirements checkWorkspaceRequirements) {

        this.offerRepository = offerRepository;
        this.publisher = publisher;
        this.checkWorkspaceRequirements = checkWorkspaceRequirements;
    }

    public IdOffer execute(IdWorkspace aWorkspaceId, OfferPeriod aPeriod, Capacity capacity) {

        checkWorkspaceRequirements.checkWorkspaceRequirements(aWorkspaceId, capacity);

        Offer offer = new Offer(aWorkspaceId, capacity, aPeriod);
        offerRepository.publish(offer);
        publisher.publish(new OfferPublished(offer.getId().value()));
        return offer.getId();
    }

}
