package com.zenika.training.freenb;

import com.zenika.training.freenb.publishing.application.CreateWorkspaceService;
import com.zenika.training.freenb.publishing.application.PublishOfferService;
import com.zenika.training.freenb.publishing.domain.offer.OfferPublisher;
import com.zenika.training.freenb.publishing.domain.offer.Offers;
import com.zenika.training.freenb.publishing.domain.workspace.CheckWorkspaceRequirements;
import com.zenika.training.freenb.publishing.domain.workspace.Workspaces;
import com.zenika.training.freenb.reservation.application.*;
import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.reservation.ReservationRefused;
import com.zenika.training.freenb.reservation.domain.reservation.Reservations;
import com.zenika.training.shared.domain_event.DomainEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public CreateWorkspaceService createWorkspaceService(Workspaces workspaces) {
        return new CreateWorkspaceService(workspaces);
    }

    @Bean
    public PublishOfferService publishOfferService(Offers offers, OfferPublisher publisher, Workspaces workspaces) {
        return new PublishOfferService(offers, publisher, new CheckWorkspaceRequirements(workspaces, offers));
    }

    @Bean
    public SearchCorrespondingOffers searchCorrespondingOffers(AvailableOffers availableOffers) {
        return new SearchCorrespondingOffers(availableOffers);
    }

    @Bean
    public AddNewAvailableOffer addNewAvailableOffer(AvailableOffers availableOffers) {
        return new AddNewAvailableOffer(availableOffers);
    }

    @Bean
    public BookReservationService bookReservationService(AvailableOffers availableOffers, Reservations reservations) {
        return new BookReservationService(availableOffers, reservations);
    }

    @Bean
    public RefuseReservationService reservationService(Reservations reservation) {
        return new RefuseReservationService(reservation);
    }

    @Bean
    public ReservationRefusedService reservationRefusedService(AvailableOffers availableOffers) {
        ReservationRefusedService reservationRefusedService = new ReservationRefusedService(availableOffers);
        DomainEventPublisher.register(reservationRefusedService::execute, ReservationRefused.class.getCanonicalName());
        return reservationRefusedService;
    }
}
