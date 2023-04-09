package example.hello;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class HelloRestControllerStandaloneRestassuredTest {

    @Test
    public void testRequest() {
        RestAssuredMockMvc
                .given()
                    .standaloneSetup(new HelloRestController())
                    .log().ifValidationFails()
                .when()
                    .get("/")
                .then()
                    .log().ifValidationFails()
                    .statusCode(SC_OK)
                    .body(equalTo("Greetings from Spring Boot!"));
    }
    @Test
    public void test500Request() {
        RestAssuredMockMvc
                .given()
                    .standaloneSetup(new HelloRestController())
                    .log().ifValidationFails()
                .when()
                    .get("/500")
                .then()
                    .log().ifValidationFails()
                    .statusCode(SC_INTERNAL_SERVER_ERROR)
                    .body(equalTo("Internal error from Spring Boot!"));
    }
    @Test
    public void test400Request() {
        RestAssuredMockMvc
                .given()
                    .standaloneSetup(new HelloRestController())
                    .log().ifValidationFails()
                .when()
                    .get("/400")
                .then()
                    .log().ifValidationFails()
                    .statusCode(SC_BAD_REQUEST)
                    .body(equalTo("Bad request, says Spring Boot!"));
    }
}
