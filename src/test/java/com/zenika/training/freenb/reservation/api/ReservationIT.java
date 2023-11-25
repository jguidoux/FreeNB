package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.reservation.application.AddNewAvailableOffer;
import com.zenika.training.freenb.reservation.application.BookReservationService;
import com.zenika.training.freenb.reservation.domain.HostId;
import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.availableoffers.OfferId;
import com.zenika.training.freenb.reservation.domain.availableoffers.Seats;
import com.zenika.training.freenb.reservation.domain.reservation.ReservationId;
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

    public static final HostId HOST = HostId.create();
    public static final OfferId OFFER_ID = new OfferId(UUID.randomUUID().toString());
    @Autowired
    AddNewAvailableOffer addOffersService;
    @Autowired
    BookReservationService reservationService;
    @LocalServerPort
    private Integer port;

    @Test
    void should_refuse_offer() {
        AvailableOffer availableOffer = new AvailableOffer(HOST, OFFER_ID, Seats.fromInt(2));
        addOffersService.execute(availableOffer);
        ReservationId reservationId = reservationService.execute(OFFER_ID).getId();

        RefuseRequest request = new RefuseRequest(HOST.value());
        Response response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when().post("http://localhost:" + port + "/v1/reservation/" + reservationId.value() + "/refused");

        response.then().statusCode(HttpStatus.NO_CONTENT.value());
    }
}
