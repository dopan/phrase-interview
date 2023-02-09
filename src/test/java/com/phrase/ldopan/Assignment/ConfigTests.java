package com.phrase.ldopan.Assignment;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ConfigTests {

    private final String URL = "http://localhost:8080/config";
    private final String USERNAME_ERROR = "Username cannot be empty!";
    private final String USERNAME_DEFAULT = "Username: <span>admin</span>";


    @Test
    @Sql("/data.sql")
    public void configLoads() {
        Response response = RestAssured.get(URL);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    @Sql("/deleteData.sql")
    public void noDefaultDbData() {
        Response response = RestAssured.get(URL);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatusCode());
    }

    @Test
    @Sql("/data.sql")
    public void changeConfigFails() throws JSONException {
        RequestSpecification request = given();
        request.header("Accept", ContentType.JSON.getAcceptHeader());
        request.param("username", "");
        request.param("password", "123");
        Response response = request.post(URL);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertTrue(response.getBody().print().contains(USERNAME_ERROR));
        assertTrue(response.getBody().print().contains(USERNAME_DEFAULT));
    }

    @Test
    @Sql("/data.sql")
    public void changeConfig() throws JSONException {
        RequestSpecification request = given();
        request.header("Accept", ContentType.JSON.getAcceptHeader());
        String newUsername = "phrase";
        request.param("username", newUsername);
        request.param("password", "123");
        Response response = request.post(URL);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertTrue(response.getBody().print().contains("Username: <span>" + newUsername + "</span>"));
    }
}
