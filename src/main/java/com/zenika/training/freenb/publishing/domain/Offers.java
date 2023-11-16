package com.zenika.training.freenb.publishing.domain;

public interface Offers {
    Offer findById(IdOffer idOffer);

    void publish(Offer offer);
}
