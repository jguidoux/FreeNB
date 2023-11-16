package com.zenika.training.freenb.publishing.infra;

import com.zenika.training.freenb.publishing.domain.IdOffer;
import com.zenika.training.freenb.publishing.domain.Offer;
import com.zenika.training.freenb.publishing.domain.Offers;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
}
