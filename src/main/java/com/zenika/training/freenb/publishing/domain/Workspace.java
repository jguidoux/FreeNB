package com.zenika.training.freenb.publishing.domain;

import com.zenika.training.shared.AggregateRoot;

public class Workspace extends AggregateRoot<IdWorkspace> {

    private final IdFreelanceHost idHost;
    private final Capacity capacity;

    public Workspace(IdFreelanceHost idHost, Capacity capacity) {
        super(IdWorkspace.create());
        this.idHost = idHost;
        this.capacity = capacity;
    }

    public IdFreelanceHost getIdHost() {
        return idHost;
    }

    public Capacity getCapacity() {
        return capacity;
    }
}
