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
        wireMockServer.givenThat(get(urlEqualTo("/api/customers"))
            .willReturn(aResponse()
            .withStatus(200)
            .withBodyFile("customerInfo.json")));
    }

    @Test
    void getCustomers() {
        Response response = given()
            .get("/customers");

        Assertions.assertEquals(200, response.statusCode());
        List<Map<String, ?>> customers = response.path("$");
        Assertions.assertTrue(customers.size() == 2);

        // This checks the keys in a customer response object
        for(Map<String, ?> customer: customers) {
            Assertions.assertTrue(customer.containsKey("first_name"));
            Assertions.assertTrue(customer.containsKey("middle_name"));
            Assertions.assertTrue(customer.containsKey("last_name"));
            Assertions.assertTrue(customer.containsKey("age"));
            Assertions.assertTrue(customer.containsKey("address"));
            Assertions.assertTrue(customer.containsKey("phone_number"));
        }

        // This checks the keys in the nested customer address response object
        for(Map<String, ?> customer: customers) {
            @SuppressWarnings("unchecked")
            Map<String, ?> address = (Map<String, ?>) customer.get("address");
            Assertions.assertTrue(address.containsKey("street_address"));
            Assertions.assertTrue(address.containsKey("city"));
            Assertions.assertTrue(address.containsKey("state"));
            Assertions.assertTrue(address.containsKey("postal_code"));
        }

        // This checks the keys in the nested customer phoneNumber response object
        for(Map<String, ?> customer: customers) {
            @SuppressWarnings("unchecked")
            ArrayList<Map<String, ?>> phoneNumbers = (ArrayList<Map<String, ?>>) customer.get("phone_number");
            for(Map<String, ?> phoneNumber: phoneNumbers) {
                Assertions.assertTrue(phoneNumber.containsKey("type"));
                Assertions.assertTrue(phoneNumber.containsKey("number"));
            }
        }
    }
}