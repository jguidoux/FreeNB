package com.zenika.training.freenb.publishing.api;

import com.zenika.training.freenb.publishing.applcation.PublishOfferService;
import com.zenika.training.freenb.publishing.domain.offer.OffId;
import com.zenika.training.freenb.publishing.domain.offer.OfferPeriod;
import com.zenika.training.freenb.publishing.domain.workspace.Capacity;
import com.zenika.training.freenb.publishing.domain.workspace.WorkspaceId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/v1/offers")
public class OfferRestController {

    private final PublishOfferService publishOfferService;

    public OfferRestController(PublishOfferService publishOfferService) {
        this.publishOfferService = publishOfferService;
    }

    @PostMapping("publish")
    public ResponseEntity<String> createNewWorkspace(@RequestBody PublishOfferRequest request) {
        OffId id = publishOfferService.execute(new WorkspaceId(request.workspaceId()),
                OfferPeriod.between(request.start(), request.end()),
                new Capacity(request.capacity()));
        return ResponseEntity.created(URI.create("/v1/offers/" + id.value())).build();
    }
}
