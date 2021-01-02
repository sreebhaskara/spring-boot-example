package example.counter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CounterRestControllerSpringBootRestassuredExceptionIT {
 
    @MockBean
    CounterService counterService;
    
    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }
    @Test
    public void testUnauthorizedRequest() {
        assertNotNull(counterService);
        when(counterService.count(notNull()))
            .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));
        RestAssured
                .given()
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .body("{\"int1\":1, \"int2\":2}")
                    .log().ifValidationFails()
                .when()
                    .post(CounterRestController.ADD_URL)
                .then()
                    .log().ifValidationFails()
                    .statusCode(SC_UNAUTHORIZED);
    }
    @Test
    public void testNPERequest() {
        assertNotNull(counterService);
        when(counterService.count(notNull()))
            .thenThrow(new NullPointerException());
        RestAssured
                .given()
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .body("{\"int1\":1, \"int2\":2}")
                    .log().ifValidationFails()
                .when()
                    .post(CounterRestController.ADD_URL)
                .then()
                    .log().ifValidationFails()
                    .statusCode(SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void testGETRequest() {
        RestAssured
                .given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\"int1\":1, \"int2\":2}")
                .log().ifValidationFails()
                .when()
                .get(CounterRestController.ADD_URL)
                .then()
                .log().ifValidationFails()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }

}
