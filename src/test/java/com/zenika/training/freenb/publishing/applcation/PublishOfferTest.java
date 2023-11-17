package com.zenika.training.freenb.publishing.applcation;

import com.zenika.training.freenb.publishing.domain.*;
import com.zenika.training.freenb.publishing.infra.OfferInMemory;
import com.zenika.training.freenb.reservation.api.OfferPublished;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class PublishOfferTest {


    @Test
    public void should_publish_offer() {

        // étant donné un workspace
        // et une période
        IdWorkspace myWorkspaceId = IdWorkspace.create();
        LocalDate start = LocalDate.of(2023, 11, 1);
        LocalDate end = LocalDate.of(2023, 11, 30);
        OfferPeriod period = OfferPeriod.between(start, end);
        Offers offerRepository = new OfferInMemory();
        PublishOfferService publishOfferService = new PublishOfferService(offerRepository, Mockito.mock(OfferPublisher.class));

        // quand je veux publier une offre
        // pour ce workspace
        IdOffer idOffer = publishOfferService.execute(myWorkspaceId, period);

        // alors une offre est publiée
        Offer offer = offerRepository.findById(idOffer);
        assertThat(offer.isPublished()).isTrue();

    }

    @Test

    void  should_notify_offer_published() {

        IdWorkspace myWorkspaceId = IdWorkspace.create();
        LocalDate start = LocalDate.of(2023, 11, 1);
        LocalDate end = LocalDate.of(2023, 11, 30);
        OfferPeriod period = OfferPeriod.between(start, end);
        Offers offerRepository = new OfferInMemory();
        OfferPublisher publisher = Mockito.mock(OfferPublisher.class);
        PublishOfferService publishOfferService = new PublishOfferService(offerRepository, publisher);

        // quand je veux publier une offre
        // pour ce workspace
        IdOffer idOffer = publishOfferService.execute(myWorkspaceId, period);

        // alors une offre est publiée
        Offer offer = offerRepository.findById(idOffer);
        assertThat(offer.isPublished()).isTrue();
        verify(publisher).publish(new OfferPublished(idOffer.value()));
    }
}
