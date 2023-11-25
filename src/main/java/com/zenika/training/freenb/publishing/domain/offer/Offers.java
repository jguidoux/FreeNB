package com.zenika.training.freenb.publishing.domain.offer;

import com.zenika.training.freenb.publishing.domain.offer.IdOffer;
import com.zenika.training.freenb.publishing.domain.offer.Offer;
import com.zenika.training.freenb.publishing.domain.workspace.IdWorkspace;

import java.util.List;

public interface Offers {
    Offer findById(IdOffer idOffer);

    void publish(Offer offer);

    List<Offer> findOfferOfWorkspace(IdWorkspace aWorkspaceId);
}
