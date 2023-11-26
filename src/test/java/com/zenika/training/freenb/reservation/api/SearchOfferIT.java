package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.TestUtils;
import com.zenika.training.freenb.reservation.domain.HostId;
import com.zenika.training.freenb.reservation.domain.availableoffers.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SearchOfferIT {

    public static final HostId HOST = HostId.create();
    @Autowired
    AvailableOffers repo;
    @LocalServerPort
    private Integer port;


    @Test
    void should_find_offers() {
        String offerId = anOfferExist();


        SearchQuery searchQuery = new SearchQuery(TestUtils.TWO_FIRST_DAYS_OF_NOVEMBER_PERIOD);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(searchQuery)
                .when().post("http://localhost:" + port + "/v1/availableOffers/search");

        response.then().statusCode(HttpStatus.OK.value())
                .body("correspondingOffers.id", hasItems(offerId));
    }

    private String anOfferExist() {
        String offerId = UUID.randomUUID().toString();
        AvailableOffer availableOffer = new AvailableOffer(HOST, new OfferId(offerId), Seats.fromInt(2), TestUtils.TWO_FIRST_DAYS_OF_NOVEMBER);
        repo.add(availableOffer);
        return offerId;
    }
}
