package integration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;

import java.util.*;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

class GetInventoryIT {
    static WireMockServer wireMockServer;

    @BeforeAll
    public static void setup() {
        wireMockServer = new WireMockServer(
        options().port(8088)
        .usingFilesUnderDirectory("src/integration_test/resources"));
        wireMockServer.start();
        RestAssured.baseURI = "http://localhost:8088/api";
        RestAssured.defaultParser = Parser.JSON;
        setupStubs();
    }

    @AfterAll
    public static void teardown() {
        wireMockServer.stop();
    }

    public static void setupStubs() {
        wireMockServer.givenThat(get(urlEqualTo("/api/inventory"))
            .willReturn(aResponse()
            .withStatus(200)
            .withBodyFile("carInventory.json")));
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