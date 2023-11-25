package com.zenika.training.freenb.publishing.infra;

import com.zenika.training.freenb.publishing.domain.offer.Offer;
import com.zenika.training.freenb.publishing.domain.offer.OfferId;
import com.zenika.training.freenb.publishing.domain.offer.Offers;
import com.zenika.training.freenb.publishing.domain.workspace.WorkspaceId;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OfferInMemory implements Offers {
    private final Map<OfferId, Offer> repo = new HashMap<>();


    @Override
    public Offer findById(OfferId idOffer) {
        return repo.get(idOffer);
    }

    @Override
    public void publish(Offer offer) {
        repo.put(offer.getId(), offer);
    }

    @Override
    public List<Offer> findOfferOfWorkspace(WorkspaceId aWorkspaceId) {
        return repo.values().stream()
                   .filter(offer -> offer.getWorkspaceId().equals(aWorkspaceId))
                   .toList();
    }
}
