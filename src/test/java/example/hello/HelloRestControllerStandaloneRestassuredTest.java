package example.hello;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

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
                    .content(equalTo("Greetings from Spring Boot!"));
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
                    .content(equalTo("Internal error from Spring Boot!"));
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
                    .content(equalTo("Bad request, says Spring Boot!"));
    }
}
