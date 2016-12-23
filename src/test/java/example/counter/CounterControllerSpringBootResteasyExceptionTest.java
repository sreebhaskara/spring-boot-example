package example.counter;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
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
public class CounterControllerSpringBootResteasyExceptionTest {
 
    @MockBean
    CounterService counterService;
    
    @Value("${local.server.port}")
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
                    .accept("application/json")
                    .contentType(ContentType.JSON)
                    .body("{\"int1\":1, \"int2\":2}")
                    .log().ifValidationFails()
                .when()
                    .post("/add")
                .then()
                    .log().ifValidationFails()
                    .statusCode(401);
    }
    @Test
    public void testNPERequest() {
        assertNotNull(counterService);
        when(counterService.count(notNull(CounterRequest.class)))
            .thenThrow(new NullPointerException());
        RestAssured
                .given()
                    .accept("application/json")
                    .contentType(ContentType.JSON)
                    .body("{\"int1\":1, \"int2\":2}")
                    .log().ifValidationFails()
                .when()
                    .post("/add")
                .then()
                    .log().ifValidationFails()
                    .statusCode(500);
    }
}
