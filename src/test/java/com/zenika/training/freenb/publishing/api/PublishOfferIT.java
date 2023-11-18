package com.zenika.training.freenb.publishing.api;

import com.zenika.training.freenb.publishing.domain.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PublishOfferIT {

    @LocalServerPort
    private Integer port;

    @SpyBean
    Offers repo;

    @Autowired
    Workspaces workspaces;

    @Test
    void should_create_new_workspace() {
        IdWorkspace idWorkspace = aWorkspaceExist();

        LocalDate start = LocalDate.of(2023, 11, 1);
        LocalDate end = LocalDate.of(2023, 11, 30);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(new PublishOfferRequest(idWorkspace.value(), start, end))
                .when().post("http://localhost:" + port + "/v1/offers/publish");


        ArgumentCaptor<Offer> captor = ArgumentCaptor.forClass(Offer.class);
        verify(repo).publish(captor.capture());

        response.then().statusCode(HttpStatus.CREATED.value())
                .header("location", "/v1/offers/" + captor.getValue().getId().value());
    }


    private IdWorkspace aWorkspaceExist() {
        Workspace newWorkspace = new Workspace(null, null);
        workspaces.create(newWorkspace);
        return newWorkspace.getId();
    }
}
