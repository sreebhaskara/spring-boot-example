package example.counter;

import static org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CounterRestControllerSpringBootRestassuredExceptionIT {
 
    @MockBean
    CounterService counterService;
    
    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }
    @Test
    public void testUnauthorizedRequest() {
        assertNotNull(counterService);
        when(counterService.count(notNull(CounterRequest.class)))
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
        when(counterService.count(notNull(CounterRequest.class)))
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
}
