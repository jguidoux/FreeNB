package com.zenika.training.freenb.publishing.domain;

import com.zenika.training.shared.AggregateRoot;

public class Offer extends AggregateRoot<IdOffer> {
    private final IdWorkspace workspaceId;

    private final Capacity capacity;
    private final OfferPeriod aPeriod;
    public Offer(IdWorkspace aWorkspaceId, Capacity capacity, OfferPeriod aPeriod) {
        super(IdOffer.create());
        this.workspaceId = aWorkspaceId;
        this.capacity = capacity;
        this.aPeriod = aPeriod;
    }

    public IdWorkspace getWorkspaceId() {
        return workspaceId;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public boolean isPublished() {
        return true;
    }

}
