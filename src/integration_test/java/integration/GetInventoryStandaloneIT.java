package integration;

import static io.restassured.RestAssured.given;

import java.util.*;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

class GetInventoryStandaloneIT {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8086/api";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    void getInventory() {
        Response response = given()
            .get("/inventory");

        Assertions.assertEquals(200, response.statusCode());
        List<Map<String, ?>> cars = response.path("$");
        Assertions.assertTrue(cars.size() == 6);

        // This checks the keys in a car response object
        for(Map<String, ?> car: cars) {
            Assertions.assertTrue(car.containsKey("id"));
            Assertions.assertTrue(car.containsKey("car_type"));
            Assertions.assertTrue(car.containsKey("car_color"));
            Assertions.assertTrue(car.containsKey("year"));
            Assertions.assertTrue(car.containsKey("make"));
            Assertions.assertTrue(car.containsKey("model"));
        }
    }
}