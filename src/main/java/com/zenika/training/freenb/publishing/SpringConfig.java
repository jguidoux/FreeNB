package com.zenika.training.freenb.publishing;

import com.zenika.training.freenb.publishing.applcation.PublishOfferService;
import com.zenika.training.freenb.publishing.application.CreateWorkspaceService;
import com.zenika.training.freenb.publishing.domain.OfferPublisher;
import com.zenika.training.freenb.publishing.domain.Offers;
import com.zenika.training.freenb.publishing.domain.Workspaces;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public CreateWorkspaceService createWorkspaceService(Workspaces workspaces) {
        return new CreateWorkspaceService(workspaces);
    }

    @Bean
    public PublishOfferService publishOfferService(Offers offers, OfferPublisher publisher) {
        return new PublishOfferService(offers, publisher);
    }
}
