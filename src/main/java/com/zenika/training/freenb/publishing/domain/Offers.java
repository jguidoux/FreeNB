package com.zenika.training.freenb.publishing.domain;

import java.util.List;

public interface Offers {
    Offer findById(IdOffer idOffer);

    void publish(Offer offer);

    List<Offer> findOfferOfWorkspace(IdWorkspace aWorkspaceId);
}
