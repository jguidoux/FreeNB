package com.zenika.training.shared;

import com.zenika.training.shared.domain_event.DomainEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AggregateRoot<Id> extends Entity<Id> {


    private List<DomainEvent> domainEvents = new ArrayList<>();

    public AggregateRoot(Id id) {
        super(id);
        this.id = id;
    }

    public List<DomainEvent> pullDomainEvents() {
        List<DomainEvent> domainEvents = this.domainEvents;
        this.domainEvents = Collections.emptyList();
        return domainEvents;
    }

    protected void record(DomainEvent domainEvent) {
        this.domainEvents.add(domainEvent);
    }
}