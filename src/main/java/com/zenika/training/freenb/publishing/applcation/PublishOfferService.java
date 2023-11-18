package com.zenika.training.freenb.publishing.applcation;

import com.zenika.training.freenb.publishing.domain.*;
import com.zenika.training.freenb.reservation.api.OfferPublished;

public class PublishOfferService {
    private final Offers offerRepository;
    private final OfferPublisher publisher;
    private final Workspaces workspaces;

    public PublishOfferService(Offers offerRepository, OfferPublisher publisher, Workspaces workspaces) {

        this.offerRepository = offerRepository;
        this.publisher = publisher;
        this.workspaces = workspaces;
    }

    public IdOffer execute(IdWorkspace aWorkspaceId, OfferPeriod aPeriod) {

        boolean workspaceExist = workspaces.exist(aWorkspaceId);
        if (!workspaceExist) {
            throw new WorkspaceDoesNotExist();
        }
        Offer offer = new Offer(aWorkspaceId, aPeriod);
        offerRepository.publish(offer);
        publisher.publish(new OfferPublished(offer.getId().value()));
        return offer.getId();
    }
}
