package com.zenika.training.freenb.publishing.domain.workspace;

import com.zenika.training.freenb.publishing.applcation.OfferCapacityOverloadWorkspaceCapacity;
import com.zenika.training.freenb.publishing.domain.offer.Offer;
import com.zenika.training.freenb.publishing.domain.offer.Offers;

import java.util.List;

public class CheckWorkspaceRequirements {
    private final Workspaces workspaces;

    private final Offers offers;

    public CheckWorkspaceRequirements(Workspaces workspaces, Offers offers) {
        this.workspaces = workspaces;
        this.offers = offers;
    }

    public Workspace checkWorkspaceRequirements(IdWorkspace aWorkspaceId, Capacity newOfferCapacity) {
        Workspace existingWorkspace = this.workspaces.findBy(aWorkspaceId);
        if (existingWorkspace == null) {
            throw new WorkspaceDoesNotExist();
        }

        Capacity allOffersCapacity = sumAllExistingOffersCapacityFor(aWorkspaceId).add(newOfferCapacity);

        if (existingWorkspace.isUpperToWorkspaceCapacity(allOffersCapacity)) {
            throw new OfferCapacityOverloadWorkspaceCapacity();
        }


        return existingWorkspace;
    }

    private Capacity sumAllExistingOffersCapacityFor(IdWorkspace aWorkspaceId) {
        List<Offer> offersOfWorkspace = offers.findOfferOfWorkspace(aWorkspaceId);
        return offersOfWorkspace.stream()
                                .map(Offer::getCapacity)
                                .reduce((a, b) -> b.add(a))
                                .orElse(Capacity.empty());
    }

}
