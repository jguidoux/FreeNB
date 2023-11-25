package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.reservation.application.AddNewAvailableOffer;
import com.zenika.training.freenb.reservation.application.BookReservationService;
import com.zenika.training.freenb.reservation.application.ReservationId;
import com.zenika.training.freenb.reservation.domain.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.OfferId;
import com.zenika.training.freenb.reservation.domain.Seats;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservationIT {

    @LocalServerPort
    private Integer port;

    @Autowired
    AddNewAvailableOffer addOffersService;

    @Autowired
    BookReservationService reservationService;

    @Test
    void should_find_offers() {
        OfferId offerId1 = new OfferId(UUID.randomUUID().toString());
        AvailableOffer availableOffer = new AvailableOffer(offerId1, Seats.fromInt(2));
        addOffersService.execute(availableOffer);
        ReservationId reservationId = reservationService.execute(offerId1).getId();

        BookingRequest bookingRequest = new BookingRequest();
        Response response = given()
                .contentType(ContentType.JSON)
                .body(bookingRequest)
                .when().post("http://localhost:" + port + "/v1/reservation/" + reservationId.value() + "/refused");

        response.then().statusCode(HttpStatus.NO_CONTENT.value());
    }
}
