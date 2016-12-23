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
}
