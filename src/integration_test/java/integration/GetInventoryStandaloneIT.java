package integration;

import org.junit.jupiter.api.*;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

class GetInventoryStandaloneIT {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8086/api";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    void firstStandaloneIT() {
        Response response = given()
            .get("/standaloneintegration1");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("First Standalone Integration Test", response.asPrettyString());
        System.out.println(String.format("\n\nThe response from http://localhost:8086/api/standaloneintegration1 was: %s\n\n", response.asPrettyString()));
    }

    @Test
    void secondStandaloneIT() {
        Response response = given()
            .get("/standaloneintegration2");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Second Standalone Integration Test", response.asPrettyString());
        System.out.println(String.format("\n\nThe response from http://localhost:8086/api/standaloneintegration2 was: %s\n\n", response.asPrettyString()));
    }
}