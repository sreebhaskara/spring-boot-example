package example.hello;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloRestControllerSpringBootRestassuredIT {
    @LocalServerPort
    private int serverPort;

    @Before
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
