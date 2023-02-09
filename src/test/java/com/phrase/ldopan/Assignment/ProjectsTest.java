package com.phrase.ldopan.Assignment;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProjectsTest {

    //TODO: not tested that much (invalid credentials, load of project fails etc...)
    private final String URL = "http://localhost:8080/";

    @Test
    public void projects() {
        Response response = RestAssured.get(URL + "projects");
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void projectsLoads() {
        Response response = RestAssured.get(URL + "fetchProjects");
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertTrue(response.getBody().print().contains("Spend more time in nature")
                || response.getBody().print().contains("Passing interview")
                || response.getBody().print().contains("Getting fun job"));
    }

}
