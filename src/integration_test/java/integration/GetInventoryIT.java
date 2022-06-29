package integration;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

class GetInventoryIT {
    static WireMockServer wireMockServer;

    @BeforeAll
    public static void setup() {
        wireMockServer = new WireMockServer(8088);
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
        wireMockServer.stubFor(get(urlEqualTo("/api/integration1"))
            .willReturn(aResponse()
            .withStatus(200)
            .withBody("First Integration Test")));

        wireMockServer.stubFor(get(urlEqualTo("/api/integration2"))
            .willReturn(aResponse()
            .withStatus(200)
            .withBody("Second Integration Test")));
    }

    @Test
    void firstIT() {
        Response response = given()
            .get("/integration1");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("First Integration Test", response.asPrettyString());
        System.out.println(String.format("\n\nThe response from http://localhost:8088/api/integration1 was: %s\n\n", response.asPrettyString()));
    }

    @Test
    void secondIT() {
        Response response = given()
            .get("/integration2");

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Second Integration Test", response.asPrettyString());
        System.out.println(String.format("\n\nThe response from http://localhost:8088/api/integration2 was: %s\n\n", response.asPrettyString()));
    }
}