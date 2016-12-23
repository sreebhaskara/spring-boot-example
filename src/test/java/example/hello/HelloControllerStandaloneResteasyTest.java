package example.hello;

import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import example.hello.HelloController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

public class HelloControllerStandaloneResteasyTest {

    @Test
    public void testRequest() {
        RestAssuredMockMvc
                .given()
                    .standaloneSetup(new HelloController())
                    .log().ifValidationFails()
                .when()
                    .get("/")
                .then()
                    .log().ifValidationFails()
                    .statusCode(200)
                    .content(equalTo("Greetings from Spring Boot!"));
    }
    @Test
    public void test500Request() {
        RestAssuredMockMvc
                .given()
                    .standaloneSetup(new HelloController())
                    .log().ifValidationFails()
                .when()
                    .get("/500")
                .then()
                    .log().ifValidationFails()
                    .statusCode(500)
                    .content(equalTo("Internal error from Spring Boot!"));
    }
    @Test
    public void test400Request() {
        RestAssuredMockMvc
                .given()
                    .standaloneSetup(new HelloController())
                    .log().ifValidationFails()
                .when()
                    .get("/400")
                .then()
                    .log().ifValidationFails()
                    .statusCode(400)
                    .content(equalTo("Bad request, says Spring Boot!"));
    }
}
