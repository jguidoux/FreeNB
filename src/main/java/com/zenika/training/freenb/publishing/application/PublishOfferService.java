package com.zenika.training.freenb.publishing.application;

import com.zenika.training.freenb.publishing.domain.offer.*;
import com.zenika.training.freenb.publishing.domain.workspace.Capacity;
import com.zenika.training.freenb.publishing.domain.workspace.CheckWorkspaceRequirements;
import com.zenika.training.freenb.publishing.domain.workspace.Workspace;
import com.zenika.training.freenb.publishing.domain.workspace.WorkspaceId;
import com.zenika.training.freenb.reservation.api.OfferPublished;

import java.time.LocalDate;
import java.util.Set;

public class PublishOfferService {
    private final Offers offerRepository;
    private final OfferPublisher publisher;
    private final CheckWorkspaceRequirements checkWorkspaceRequirements;

    public PublishOfferService(Offers offerRepository, OfferPublisher publisher, CheckWorkspaceRequirements checkWorkspaceRequirements) {

        this.offerRepository = offerRepository;
        this.publisher = publisher;
        this.checkWorkspaceRequirements = checkWorkspaceRequirements;
    }

    public OfferId execute(WorkspaceId aWorkspaceId, OfferPeriod aPeriod, Capacity capacity) {

        Workspace workspace = checkWorkspaceRequirements.checkWorkspaceRequirements(aWorkspaceId, capacity);

        Offer offer = new Offer(aWorkspaceId, capacity, aPeriod);
        offerRepository.publish(offer);
        Set<LocalDate> days = offer.getListOfDays();
        publisher.publish(new OfferPublished(workspace.getIdHost().value(), offer.getId().value(), offer.getCapacity()
                                                                                                        .value(), days));
        return offer.getId();
    }

}
