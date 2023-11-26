package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.reservation.domain.HostId;
import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffer;
import com.zenika.training.freenb.reservation.domain.availableoffers.AvailableOffers;
import com.zenika.training.freenb.reservation.domain.availableoffers.OfferId;
import com.zenika.training.freenb.reservation.domain.availableoffers.Seats;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.text.IsEmptyString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static com.zenika.training.freenb.TestUtils.NOV_1;
import static com.zenika.training.freenb.TestUtils.NOV_2;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.not;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookOfferIT {

    public static final HostId HOST = HostId.create();
    public static final Seats TWO_FREE_SEATS = Seats.fromInt(2);
    @Autowired
    AvailableOffers availableOffers;
    @LocalServerPort
    private Integer port;

    @Test
    void should_find_offers() {
        String offerId = anOfferIsAvailable();

        BookingRequest bookingRequest = new BookingRequest(NOV_1, NOV_2);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(bookingRequest)
                .when().post("http://localhost:" + port + "/v1/availableOffers/" + offerId + "/book");

        response.then().statusCode(HttpStatus.OK.value())
                .body("idReservation", not(IsEmptyString.emptyOrNullString()));
    }

    private String anOfferIsAvailable() {
        String offerId = UUID.randomUUID().toString();
        Set<LocalDate> days = Set.of(NOV_1, NOV_2);
        AvailableOffer availableOffer = new AvailableOffer(HOST, new OfferId(offerId), TWO_FREE_SEATS, days);
        availableOffers.add(availableOffer);
        return offerId;
    }
}
