package com.zenika.training.freenb.publishing.api;

import com.zenika.training.freenb.publishing.domain.workspace.Workspace;
import com.zenika.training.freenb.publishing.domain.workspace.Workspaces;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.verify;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CreateWorkspaceIT {

    @LocalServerPort
    private Integer port;

    @SpyBean
    Workspaces repo;

    @Test
    void should_create_new_workspace() {

        Response response = given()
                .contentType(ContentType.JSON)
                .body(new WorkspaceRequest("1", 5))
                .when().post("http://localhost:" + port + "/v1/workspaces");


        ArgumentCaptor<Workspace> captor = ArgumentCaptor.forClass(Workspace.class);
        verify(repo).create(captor.capture());

        response.then().statusCode(HttpStatus.CREATED.value())
                .header("location", "/v1/workspaces/" + captor.getValue().getId().value());
    }
}
