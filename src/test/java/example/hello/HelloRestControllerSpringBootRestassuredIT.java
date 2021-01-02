package example.hello;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloRestControllerSpringBootRestassuredIT {
    @LocalServerPort
    private int serverPort;

    @BeforeEach
    public void setUp() {
        // port for test to connect to
        RestAssured.port = serverPort;
    }
    @Test
    public void testRequest() {
        io.restassured.RestAssured
                .given()
                    .log().ifValidationFails()
                .when()
                    .get("/")
                .then()
                    .log().ifValidationFails()
                    .statusCode(SC_OK)
                    .content(equalTo("Greetings from Spring Boot!"));
    }
}
