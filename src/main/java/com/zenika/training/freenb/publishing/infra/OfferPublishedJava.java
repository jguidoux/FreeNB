package com.zenika.training.freenb.publishing.infra;

import com.zenika.training.freenb.publishing.domain.OfferPublisher;
import com.zenika.training.freenb.reservation.api.OfferPublished;
import com.zenika.training.freenb.reservation.api.OfferPublishedConsumer;
import org.springframework.stereotype.Service;

@Service
public class OfferPublishedJava implements OfferPublisher {

    private final OfferPublishedConsumer consumer;

    public OfferPublishedJava(OfferPublishedConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void publish(OfferPublished offerPublished) {
        consumer.receive(offerPublished);

    }
}
