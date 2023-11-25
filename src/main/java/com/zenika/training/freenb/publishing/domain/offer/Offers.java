package com.zenika.training.freenb.publishing.domain.offer;

import com.zenika.training.freenb.publishing.domain.workspace.WorkspaceId;

import java.util.List;

public interface Offers {
    Offer findById(OffId idOffer);

    void publish(Offer offer);

    List<Offer> findOfferOfWorkspace(WorkspaceId aWorkspaceId);
}
