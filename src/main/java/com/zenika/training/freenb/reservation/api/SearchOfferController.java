package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.reservation.application.SearchCorrespondingOffers;
import com.zenika.training.freenb.reservation.application.SearchQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/availableOffers/")
public class SearchOfferController {

    private final SearchCorrespondingOffers service;

    public SearchOfferController(SearchCorrespondingOffers service) {
        this.service = service;
    }

    @PostMapping("/search")
    public ResponseEntity<CorrespondingOffersResponse> searchCorrespondingOffers(@RequestBody SearchQuery query) {
        return ResponseEntity.ok(new CorrespondingOffersResponse(service.execute(query).stream()
                                                                        .map(correspondingOffer -> new CorrespondingOfferResponse(correspondingOffer.id()
                                                                                                                                                    .value()))
                                                                        .toList()));
    }
}
