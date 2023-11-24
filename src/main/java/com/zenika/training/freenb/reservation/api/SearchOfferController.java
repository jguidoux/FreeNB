package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.reservation.application.BookReservationService;
import com.zenika.training.freenb.reservation.application.Reservation;
import com.zenika.training.freenb.reservation.application.SearchCorrespondingOffers;
import com.zenika.training.freenb.reservation.application.SearchQuery;
import com.zenika.training.freenb.reservation.domain.OfferId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/availableOffers/")
public class SearchOfferController {

    private final SearchCorrespondingOffers searchService;

    private final BookReservationService bookingService;

    public SearchOfferController(SearchCorrespondingOffers service, BookReservationService bookingService) {
        this.searchService = service;
        this.bookingService = bookingService;
    }

    @PostMapping("/search")
    public ResponseEntity<CorrespondingOffersResponse> searchCorrespondingOffers(@RequestBody SearchQuery query) {
        return ResponseEntity.ok(new CorrespondingOffersResponse(searchService.execute(query).stream()
                                                                                 .map(correspondingOffer -> new CorrespondingOfferResponse(correspondingOffer.id()
                                                                                                                                                    .value()))
                                                                              .toList()));
    }

    @PostMapping("/book")
    public ResponseEntity<BookingResponse> book(@RequestBody BookingRequest query) {
        Reservation reservation = bookingService.execute(new OfferId(query.availableOfferId()));
        return ResponseEntity.ok(new BookingResponse(reservation.getId().value()));
    }
}
