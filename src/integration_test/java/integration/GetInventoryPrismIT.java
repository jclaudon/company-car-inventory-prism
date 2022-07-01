package integration;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

class GetInventoryPrismIT {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://127.0.0.1:4010/inventory";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    void getInventoryStatic() {
        Response response = given()
        .auth().basic("", "")
        .get("/car/");

        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.asPrettyString());
    }

    @Test
    void getInventoryDynamic() {
        Response response = given()
        .auth().basic("", "")
        .get("/car/?__dynamic=true");

        Assertions.assertEquals(200, response.statusCode());
        System.out.println(response.asPrettyString());
    }
}