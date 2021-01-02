package example.counter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
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
public class CounterMVCControllerSpringBootRestassuredIT {
    @LocalServerPort
    private int serverPort;

    @BeforeEach
    public void setUp() {
        // port for test to connect to
        RestAssured.port = serverPort;
    }
    @Test
    public void testRequest() {
        RestAssured
                .given()
                    .accept(ContentType.HTML)
                    .log().ifValidationFails()
                .when()
                    .get(CounterMVCController.COUNTER_VIEW_URL)
                .then()
                    .log().ifValidationFails()
                    .statusCode(SC_OK)
                    .body("**.findAll { it.@class == 'form-control' }[0].@placeholder",
                        equalTo("First number") )
                    .body("**.findAll { it.@class == 'form-control' }[1].@placeholder",
                        equalTo("Second number") )
        ;
    }
}
