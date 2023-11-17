package com.zenika.training.freenb.publishing.infra;

import com.zenika.training.freenb.publishing.domain.OfferPublisher;
import com.zenika.training.freenb.reservation.api.OfferPublished;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OfferPublishedJava implements OfferPublisher {

    private final ApplicationEventPublisher publisher;

    public OfferPublishedJava(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publish(OfferPublished offerPublished) {
        publisher.publishEvent(offerPublished);

    }
}
