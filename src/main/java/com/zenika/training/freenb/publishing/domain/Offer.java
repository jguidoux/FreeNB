package com.zenika.training.freenb.publishing.domain;

import com.zenika.training.shared.AggregateRoot;

public class Offer extends AggregateRoot<IdOffer> {
    private final IdWorkspace aWorkspaceId;
    private final OfferPeriod aPeriod;

    public Offer(IdWorkspace aWorkspaceId, OfferPeriod aPeriod) {
        super(IdOffer.create());
        this.aWorkspaceId = aWorkspaceId;
        this.aPeriod = aPeriod;
    }

    public boolean isPublished() {
        return true;
    }
}
