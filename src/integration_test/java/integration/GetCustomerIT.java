package integration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;

import java.util.Map;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

class GetCustomerIT {
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
        wireMockServer.stubFor(get(urlEqualTo("/api/getcustomerfile"))
            .willReturn(aResponse()
            .withStatus(200)
            .withBodyFile("customerInfoResponseBody.json")));
    }

    @Test
    void getCustomerFileIT() {
        Response response = given()
            .get("/getcustomerfile");

        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.asPrettyString());
        Map<String, String> customer = response.jsonPath().getMap("$");
        Assertions.assertEquals("Smith", customer.get("lastName"));
        System.out.println(String.format("\n\nThe response from http://localhost:8088/api/getcustomerfile was: %s\n\n", customer.toString()));
    }
}