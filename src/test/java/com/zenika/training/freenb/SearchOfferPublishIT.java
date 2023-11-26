package com.zenika.training.freenb;

import com.zenika.training.freenb.publishing.api.PublishOfferRequest;
import com.zenika.training.freenb.publishing.domain.FreelanceHostId;
import com.zenika.training.freenb.publishing.domain.workspace.Capacity;
import com.zenika.training.freenb.publishing.domain.workspace.Workspace;
import com.zenika.training.freenb.publishing.domain.workspace.WorkspaceId;
import com.zenika.training.freenb.publishing.domain.workspace.Workspaces;
import com.zenika.training.freenb.reservation.domain.availableoffers.SearchQuery;
import com.zenika.training.freenb.reservation.domain.reservation.PeriodCriteria;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SearchOfferPublishIT {

    public static final FreelanceHostId HOST = FreelanceHostId.create();
    @Autowired
    Workspaces workspaces;
    @LocalServerPort
    private Integer port;

    @Test
    void should_find_corresponding_offer_when_offer_published() {
        WorkspaceId idWorkspace = aWorkspaceExist();

        LocalDate start = LocalDate.of(2023, 11, 1);
        LocalDate end = LocalDate.of(2023, 11, 30);
        int capacity = 2;

        Response post = given().contentType(ContentType.JSON)
                               .body(new PublishOfferRequest(idWorkspace.value(), start, end, capacity))
                               .when().post("http://localhost:" + port + "/v1/offers/publish");

        String[] location = post.header("location").split("/");
        String offerId = location[location.length - 1];

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


    private WorkspaceId aWorkspaceExist() {
        Workspace newWorkspace = new Workspace(HOST, new Capacity(10));
        workspaces.create(newWorkspace);
        return newWorkspace.getId();
    }
}
