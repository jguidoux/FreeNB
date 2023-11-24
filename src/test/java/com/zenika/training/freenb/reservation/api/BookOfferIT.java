package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.reservation.application.Reservation;
import com.zenika.training.freenb.reservation.application.SearchQuery;
import com.zenika.training.freenb.reservation.domain.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.text.IsEmptyString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookOfferIT {

    @LocalServerPort
    private Integer port;

    @Autowired
    AvailableOffers availableOffers;

    @Test
    void should_find_offers() {
        String offerId = UUID.randomUUID().toString();
        AvailableOffer availableOffer = new AvailableOffer(new OfferId(offerId), Seats.fromInt(2));
        availableOffers.add(availableOffer);

        BookingRequest bookingRequest = new BookingRequest(offerId);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(bookingRequest)
                .when().post("http://localhost:" + port + "/v1/availableOffers/book");

        response.then().statusCode(HttpStatus.OK.value())
                .body("idReservation", not(IsEmptyString.emptyOrNullString()));
    }
}
