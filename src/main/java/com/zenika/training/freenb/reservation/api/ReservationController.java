package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.reservation.application.RefusedReservationService;
import com.zenika.training.freenb.reservation.application.ReservationId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/reservation/")
public class ReservationController {

    private final RefusedReservationService bookingService;

    public ReservationController(RefusedReservationService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping("{reservationId}/refused")
    public ResponseEntity<Void> refused(@PathVariable String reservationId) {
        bookingService.execute(ReservationId.fromString(reservationId));
        return ResponseEntity.noContent().build();
    }
}
