package hello;

import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

public class HelloControllerResteasyTest {

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
}
