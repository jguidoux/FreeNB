package com.zenika.training.freenb.reservation.api;

import com.zenika.training.freenb.reservation.domain.HostId;
import com.zenika.training.freenb.reservation.domain.PeriodCriteria;
import com.zenika.training.freenb.reservation.domain.availableoffers.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.Set;
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
    private Set<LocalDate> days;

    @BeforeEach
    void setUp() {
        LocalDate day1 = LocalDate.of(2023, 11, 1);
        LocalDate day2 = LocalDate.of(2023, 11, 2);
        days = Set.of(day1, day2);
    }

    @Test
    void should_find_offers() {
        String offerId = UUID.randomUUID().toString();
        AvailableOffer availableOffer = new AvailableOffer(HOST, new OfferId(offerId), Seats.fromInt(2), days);
        repo.add(availableOffer);

        LocalDate from = LocalDate.of(2023, 11, 1);
        LocalDate to = LocalDate.of(2023, 11, 2);
        PeriodCriteria period = PeriodCriteria.between(from, to);

        SearchQuery searchQuery = new SearchQuery(period);
        Response response = given()
                .contentType(ContentType.JSON)
                .body(searchQuery)
                .when().post("http://localhost:" + port + "/v1/availableOffers/search");

        response.then().statusCode(HttpStatus.OK.value())
                .body("correspondingOffers.id", hasItems(offerId));
    }
}
