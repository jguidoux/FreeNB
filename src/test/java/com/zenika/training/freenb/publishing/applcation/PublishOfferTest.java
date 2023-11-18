package com.zenika.training.freenb.publishing.applcation;

import com.zenika.training.freenb.publishing.domain.*;
import com.zenika.training.freenb.publishing.infra.OfferInMemory;
import com.zenika.training.freenb.publishing.infra.WorkspacesInMemory;
import com.zenika.training.freenb.reservation.api.OfferPublished;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

public class PublishOfferTest {


    PublishOfferService publishOfferService;
    Offers offerRepository;
    OfferPublisher publisher;
    WorkspacesInMemory workspaces;

    @BeforeEach
    void setUp() {
        offerRepository = new OfferInMemory();
        publisher = Mockito.mock(OfferPublisher.class);
        workspaces = new WorkspacesInMemory();
        publishOfferService = new PublishOfferService(offerRepository, publisher, workspaces);

    }

    @Test
    public void should_publish_offer() {

        // étant donné un workspace
        // et une période
        IdWorkspace myWorkspaceId = aWorkspaceExist();

        LocalDate start = LocalDate.of(2023, 11, 1);
        LocalDate end = LocalDate.of(2023, 11, 30);
        OfferPeriod period = OfferPeriod.between(start, end);

        // quand je veux publier une offre
        // pour ce workspace
        IdOffer idOffer = publishOfferService.execute(myWorkspaceId, period);

        // alors une offre est publiée
        Offer offer = offerRepository.findById(idOffer);
        assertThat(offer.isPublished()).isTrue();

    }

    @Test
    void should_notify_offer_published() {

        IdWorkspace myWorkspaceId = aWorkspaceExist();
        LocalDate start = LocalDate.of(2023, 11, 1);
        LocalDate end = LocalDate.of(2023, 11, 30);
        OfferPeriod period = OfferPeriod.between(start, end);

        // quand je veux publier une offre
        // pour ce workspace
        IdOffer idOffer = publishOfferService.execute(myWorkspaceId, period);

        // alors une offre est publiée
        Offer offer = offerRepository.findById(idOffer);
        assertThat(offer.isPublished()).isTrue();
        verify(publisher).publish(new OfferPublished(idOffer.value()));
    }

    @Test
    void should_not_be_able_to_create_an_offer_when_workspace_does_not_exist() {

        IdWorkspace myWorkspaceId = IdWorkspace.create();
        LocalDate start = LocalDate.of(2023, 11, 1);
        LocalDate end = LocalDate.of(2023, 11, 30);
        OfferPeriod period = OfferPeriod.between(start, end);

        // quand je veux publier une offre
        // pour ce workspace
        assertThatThrownBy(() -> publishOfferService.execute(myWorkspaceId, period))
                .isInstanceOf(WorkspaceDoesNotExist.class);
    }

    private IdWorkspace aWorkspaceExist() {
        Workspace newWorkspace = new Workspace(null, null);
        workspaces.create(newWorkspace);
        return newWorkspace.getId();
    }
}
