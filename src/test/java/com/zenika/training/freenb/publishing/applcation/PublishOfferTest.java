package com.zenika.training.freenb.publishing.applcation;

import com.zenika.training.freenb.publishing.domain.*;
import com.zenika.training.freenb.publishing.domain.offer.*;
import com.zenika.training.freenb.publishing.domain.workspace.*;
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


    public static final FreelanceHostId HOST = FreelanceHostId.create();
    PublishOfferService publishOfferService;
    Offers offerRepository;
    OfferPublisher publisher;
    WorkspacesInMemory workspaces;

    Offers offers;
    @BeforeEach
    void setUp() {
        offerRepository = new OfferInMemory();
        publisher = Mockito.mock(OfferPublisher.class);
        workspaces = new WorkspacesInMemory();
        offers = new OfferInMemory();
        publishOfferService = new PublishOfferService(offerRepository, publisher, new CheckWorkspaceRequirements(workspaces, offers));
    }

    @Test
    void should_publish_offer() {

        // étant donné un workspace
        // et une période
        WorkspaceId myWorkspaceId = aWorkspaceExist();

        LocalDate start = LocalDate.of(2023, 11, 1);
        LocalDate end = LocalDate.of(2023, 11, 30);
        OfferPeriod period = OfferPeriod.between(start, end);

        // quand je veux publier une offre
        // pour ce workspace
        OfferId idOffer = publishOfferService.execute(myWorkspaceId, period, new Capacity(2));

        // alors une offre est publiée
        Offer offer = offerRepository.findById(idOffer);
        assertThat(offer.isPublished()).isTrue();

    }

    @Test
    void should_notify_offer_published() {

        WorkspaceId myWorkspaceId = aWorkspaceExist();
        LocalDate start = LocalDate.of(2023, 11, 1);
        LocalDate end = LocalDate.of(2023, 11, 30);
        OfferPeriod period = OfferPeriod.between(start, end);

        // quand je veux publier une offre
        // pour ce workspace
        OfferId idOffer = publishOfferService.execute(myWorkspaceId, period, new Capacity(2));

        // alors une offre est publiée
        Offer offer = offerRepository.findById(idOffer);
        assertThat(offer.isPublished()).isTrue();
        verify(publisher).publish(new OfferPublished(HOST.value(), idOffer.value(), offer.getCapacity().value()));
    }

    @Test
    void should_not_be_able_to_create_an_offer_when_workspace_does_not_exist() {

        WorkspaceId myWorkspaceId = WorkspaceId.create();
        LocalDate start = LocalDate.of(2023, 11, 1);
        LocalDate end = LocalDate.of(2023, 11, 30);
        OfferPeriod period = OfferPeriod.between(start, end);

        // quand je veux publier une offre
        // pour ce workspace
        assertThatThrownBy(() -> publishOfferService.execute(myWorkspaceId, period, new Capacity(2)))
                .isInstanceOf(WorkspaceDoesNotExist.class);
    }

    @Test
    void should_not_be_able_to_create_an_offer_when_capacity_superior_to_workspace_capacity() {

        WorkspaceId myWorkspaceId = aWorkspaceExistWithCapacity(1);
        LocalDate start = LocalDate.of(2023, 11, 1);
        LocalDate end = LocalDate.of(2023, 11, 30);
        OfferPeriod period = OfferPeriod.between(start, end);

        // quand je veux publier une offre
        // pour ce workspace
        assertThatThrownBy(() -> publishOfferService.execute(myWorkspaceId, period, new Capacity(2)))
                .isInstanceOf(OfferCapacityOverloadWorkspaceCapacity.class);

    }

    @Test
    void should_not_be_able_to_create_an_offer_when_all_offers_capacity_are_superior_to_workspace_capacity() {

        WorkspaceId myWorkspaceId = aWorkspaceExistWithCapacity(5);
        offerOfCapacityIsPublished(myWorkspaceId, 1);
        offerOfCapacityIsPublished(myWorkspaceId, 3);
        LocalDate start = LocalDate.of(2023, 11, 1);
        LocalDate end = LocalDate.of(2023, 11, 30);
        OfferPeriod period = OfferPeriod.between(start, end);

        // quand je veux publier une offre
        // pour ce workspace
        assertThatThrownBy(() -> publishOfferService.execute(myWorkspaceId, period, new Capacity(2)))
                .isInstanceOf(OfferCapacityOverloadWorkspaceCapacity.class);

    }

    private void offerOfCapacityIsPublished(WorkspaceId idWorkspace, int capacity) {
        offers.publish(new Offer(idWorkspace, new Capacity(capacity), null));
    }


    private WorkspaceId aWorkspaceExistWithCapacity(int capacity) {
        Workspace newWorkspace = new Workspace(null, new Capacity(capacity));
        workspaces.create(newWorkspace);
        return newWorkspace.getId();

    }

    private WorkspaceId aWorkspaceExist() {
        Workspace newWorkspace = new Workspace(HOST, new Capacity(10));
        workspaces.create(newWorkspace);
        return newWorkspace.getId();
    }
}
