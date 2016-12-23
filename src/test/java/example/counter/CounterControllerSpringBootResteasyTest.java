package example.counter;

import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import example.Application;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration({Application.class})
@WebAppConfiguration
@IntegrationTest(value = {"server.port=0"})
public class CounterControllerSpringBootResteasyTest {
    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }
    @Test
    public void testRequest() {
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
                    .statusCode(200)
                    .content("value",equalTo(3));
    }
}
