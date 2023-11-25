package com.zenika.training.freenb.publishing.domain.workspace;

import com.zenika.training.freenb.publishing.domain.FreelanceHostId;
import com.zenika.training.shared.AggregateRoot;
import lombok.Getter;

public class Workspace extends AggregateRoot<WorkspaceId> {

    private final FreelanceHostId idHost;
    private final Capacity capacity;

    public Workspace(FreelanceHostId idHost, Capacity capacity) {
        super(WorkspaceId.create());
        this.idHost = idHost;
        this.capacity = capacity;
    }

    public boolean isUpperToWorkspaceCapacity(Capacity capacity) {
        return this.capacity.value() <= capacity.value();
    }

    public FreelanceHostId getIdHost() {
        return idHost;
    }

    public Capacity getCapacity() {
        return capacity;
    }
}
