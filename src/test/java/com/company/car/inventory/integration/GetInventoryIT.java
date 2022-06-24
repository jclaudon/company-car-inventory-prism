package com.company.car.inventory.integration;

import org.junit.jupiter.api.*;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

class GetInventoryIT {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/api";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    void firstIT() {
        Response response = given()
            .get("/integration1");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("First Integration Test", response.asPrettyString());
        System.out.println(String.format("\n\nThe response from http://localhost:8080/api/integration1 was: %s\n\n", response.asPrettyString()));
    }

    @Test
    void secondIT() {
        Response response = given()
            .get("/integration2");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Second Integration Test", response.asPrettyString());
        System.out.println(String.format("\n\nThe response from http://localhost:8080/api/integration2 was: %s\n\n", response.asPrettyString()));
    }
}