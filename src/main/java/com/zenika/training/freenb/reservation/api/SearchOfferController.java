package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.reservation.application.BookReservationService;
import com.zenika.training.freenb.reservation.application.SearchCorrespondingOffers;
import com.zenika.training.freenb.reservation.domain.availableoffers.CorrespondingOffer;
import com.zenika.training.freenb.reservation.domain.availableoffers.OfferId;
import com.zenika.training.freenb.reservation.domain.availableoffers.SearchQuery;
import com.zenika.training.freenb.reservation.domain.reservation.PeriodCriteria;
import com.zenika.training.freenb.reservation.domain.reservation.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("{offerId}/book")
    public ResponseEntity<BookingResponse> book(@PathVariable String offerId, @RequestBody BookingRequest request) {
        OfferId offerId1 = new OfferId(offerId);
        CorrespondingOffer correspondingOffer = new CorrespondingOffer(offerId1, PeriodCriteria.between(request.from(), request.to()));
        Reservation reservation = bookingService.execute(correspondingOffer);
        return ResponseEntity.ok(new BookingResponse(reservation.getId().value()));
    }
}
