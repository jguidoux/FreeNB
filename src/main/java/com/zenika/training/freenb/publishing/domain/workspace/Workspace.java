package com.zenika.training.freenb.publishing.domain.workspace;

import com.zenika.training.freenb.publishing.domain.IdFreelanceHost;
import com.zenika.training.freenb.publishing.domain.workspace.Capacity;
import com.zenika.training.freenb.publishing.domain.workspace.IdWorkspace;
import com.zenika.training.shared.AggregateRoot;

public class Workspace extends AggregateRoot<IdWorkspace> {

    private final IdFreelanceHost idHost;
    private final Capacity capacity;

    public Workspace(IdFreelanceHost idHost, Capacity capacity) {
        super(IdWorkspace.create());
        this.idHost = idHost;
        this.capacity = capacity;
    }

    public boolean isUpperToWorkspaceCapacity(Capacity capacity) {
        return this.capacity.value() <= capacity.value();
    }

    public IdFreelanceHost getIdHost() {
        return idHost;
    }

    public Capacity getCapacity() {
        return capacity;
    }
}