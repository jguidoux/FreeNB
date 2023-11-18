package com.zenika.training.freenb;

import com.zenika.training.freenb.publishing.applcation.PublishOfferService;
import com.zenika.training.freenb.publishing.application.CreateWorkspaceService;
import com.zenika.training.freenb.publishing.domain.OfferPublisher;
import com.zenika.training.freenb.publishing.domain.Offers;
import com.zenika.training.freenb.publishing.domain.Workspaces;
import com.zenika.training.freenb.reservation.application.AddNewAvailableOffer;
import com.zenika.training.freenb.reservation.application.SearchCorrespondingOffers;
import com.zenika.training.freenb.reservation.domain.AvailableOffers;
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
        return new PublishOfferService(offers, publisher, workspaces);
    }

    @Bean
    public SearchCorrespondingOffers searchCorrespondingOffers(AvailableOffers availableOffes) {
        return new SearchCorrespondingOffers(availableOffes);
    }

    @Bean
    public AddNewAvailableOffer addNewAvailableOffer(AvailableOffers availableOffers) {
        return new AddNewAvailableOffer(availableOffers);
    }
}
