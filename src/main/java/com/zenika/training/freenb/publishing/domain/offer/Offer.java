package com.zenika.training.freenb.publishing.domain.offer;

import com.zenika.training.freenb.publishing.domain.workspace.Capacity;
import com.zenika.training.freenb.publishing.domain.workspace.WorkspaceId;
import com.zenika.training.shared.AggregateRoot;

public class Offer extends AggregateRoot<OffId> {
    private final WorkspaceId workspaceId;

    private final Capacity capacity;
    private final OfferPeriod aPeriod;
    public Offer(WorkspaceId aWorkspaceId, Capacity capacity, OfferPeriod aPeriod) {
        super(OffId.create());
        this.workspaceId = aWorkspaceId;
        this.capacity = capacity;
        this.aPeriod = aPeriod;
    }

    public WorkspaceId getWorkspaceId() {
        return workspaceId;
    }

    public Capacity getCapacity() {
        return capacity;
    }

    public boolean isPublished() {
        return true;
    }

}
