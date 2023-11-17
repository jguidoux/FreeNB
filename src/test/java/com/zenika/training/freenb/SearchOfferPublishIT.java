package com.zenika.training.freenb;

import com.zenika.training.freenb.publishing.api.PublishOfferRequest;
import com.zenika.training.freenb.reservation.application.SearchQuery;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SearchOfferPublishIT {

    @LocalServerPort
    private Integer port;


    @Test
    void should_find_corresponding_offer_when_offer_published() {

        LocalDate start = LocalDate.of(2023, 11, 1);
        LocalDate end = LocalDate.of(2023, 11, 30);

        Response post = given().contentType(ContentType.JSON)
                               .body(new PublishOfferRequest("1", start, end))
                               .when().post("http://localhost:" + port + "/v1/offers/publish");

        String[] location = post.header("location").split("/");
        String offerId = location[location.length - 1];

        SearchQuery searchQuery = new SearchQuery();
        Response response = given()
                .contentType(ContentType.JSON)
                .body(searchQuery)
                .when().post("http://localhost:" + port + "/v1/availableOffers/search");

        response.then().statusCode(HttpStatus.OK.value())
                .body("correspondingOffers.id", hasItems(offerId));
    }
}
