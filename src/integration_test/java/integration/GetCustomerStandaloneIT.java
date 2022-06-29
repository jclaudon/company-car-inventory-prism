package integration;

import org.junit.jupiter.api.*;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.util.Map;

class GetCustomerStandaloneIT {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8086/api";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    void getCustomerStandaloneIT() {
        Response response = given()
            .get("/getcustomer");

        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.asPrettyString());
        Map<String, String> customer = response.jsonPath().getMap("$");
        Assertions.assertEquals("John", customer.get("firstName"));
        System.out.println(String.format("\n\nThe response from http://localhost:8086/api/getcustomer was: %s\n\n", customer.toString()));
    }

    @Test
    void getCustomerFileStandaloneIT() {
        Response response = given()
            .get("/getcustomerfile");

        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.asPrettyString());
        Map<String, String> customer = response.jsonPath().getMap("$");
        Assertions.assertEquals("Smith", customer.get("lastName"));
        System.out.println(String.format("\n\nThe response from http://localhost:8086/api/getcustomerfile was: %s\n\n", customer.toString()));
    }
}