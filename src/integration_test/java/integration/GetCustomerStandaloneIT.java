package integration;

import static io.restassured.RestAssured.given;

import java.util.*;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

class GetCustomerStandaloneIT {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8086/api";
        RestAssured.defaultParser = Parser.JSON;
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