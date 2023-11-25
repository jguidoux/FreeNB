package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.publishing.domain.IdFreelanceHost;
import com.zenika.training.freenb.reservation.domain.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.OfferId;
import com.zenika.training.freenb.reservation.domain.Seats;
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
import static org.hamcrest.Matchers.not;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookOfferIT {

    public static final IdFreelanceHost HOST = IdFreelanceHost.create();
    @LocalServerPort
    private Integer port;

    @Autowired
    AvailableOffers availableOffers;

    @Test
    void should_find_offers() {
        String offerId = UUID.randomUUID().toString();
        AvailableOffer availableOffer = new AvailableOffer(HOST, new OfferId(offerId), Seats.fromInt(2));
        availableOffers.add(availableOffer);

        BookingRequest bookingRequest = new BookingRequest();
        Response response = given()
                .contentType(ContentType.JSON)
                .body(bookingRequest)
                .when().post("http://localhost:" + port + "/v1/availableOffers/" + offerId + "/book");

        response.then().statusCode(HttpStatus.OK.value())
                .body("idReservation", not(IsEmptyString.emptyOrNullString()));
    }
}
