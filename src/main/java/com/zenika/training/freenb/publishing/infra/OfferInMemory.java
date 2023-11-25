package com.zenika.training.freenb.publishing.infra;

import com.zenika.training.freenb.publishing.domain.offer.IdOffer;
import com.zenika.training.freenb.publishing.domain.workspace.IdWorkspace;
import com.zenika.training.freenb.publishing.domain.offer.Offer;
import com.zenika.training.freenb.publishing.domain.offer.Offers;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OfferInMemory implements Offers {
    private final Map<IdOffer, Offer> repo = new HashMap<>();


    @Override
    public Offer findById(IdOffer idOffer) {
        return repo.get(idOffer);
    }

    @Override
    public void publish(Offer offer) {
        repo.put(offer.getId(), offer);
    }

    @Override
    public List<Offer> findOfferOfWorkspace(IdWorkspace aWorkspaceId) {
        return repo.values().stream()
                .filter(offer -> offer.getWorkspaceId().equals(aWorkspaceId))
                .toList();
    }
}
