package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.publishing.domain.IdFreelanceHost;
import com.zenika.training.freenb.reservation.application.RefuseReservationService;
import com.zenika.training.freenb.reservation.domain.ReservationId;
import com.zenika.training.freenb.reservation.domain.RefuseReservationCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/reservation/")
public class ReservationController {

    private final RefuseReservationService bookingService;

    public ReservationController(RefuseReservationService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping("{reservationId}/refused")
    public ResponseEntity<Void> refused(@PathVariable String reservationId, @RequestBody RefuseRequest request) {
        IdFreelanceHost host = IdFreelanceHost.fromString(request.hostId());
        ReservationId reservation = ReservationId.fromString(reservationId);
        bookingService.execute(new RefuseReservationCommand(reservation, host));
        return ResponseEntity.noContent().build();
    }
}
