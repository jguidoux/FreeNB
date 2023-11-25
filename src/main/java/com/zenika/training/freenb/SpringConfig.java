package com.zenika.training.freenb;

import com.zenika.training.freenb.publishing.applcation.PublishOfferService;
import com.zenika.training.freenb.publishing.application.CreateWorkspaceService;
import com.zenika.training.freenb.publishing.domain.CheckWorkspaceRequirements;
import com.zenika.training.freenb.publishing.domain.OfferPublisher;
import com.zenika.training.freenb.publishing.domain.Offers;
import com.zenika.training.freenb.publishing.domain.Workspaces;
import com.zenika.training.freenb.reservation.application.AddNewAvailableOffer;
import com.zenika.training.freenb.reservation.application.BookReservationService;
import com.zenika.training.freenb.reservation.application.RefusedReservationService;
import com.zenika.training.freenb.reservation.application.SearchCorrespondingOffers;
import com.zenika.training.freenb.reservation.domain.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.Reservations;
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
    public RefusedReservationService reservationService(Reservations reservation, AvailableOffers availableOffers) {
        return new RefusedReservationService(reservation, availableOffers);
    }
}
