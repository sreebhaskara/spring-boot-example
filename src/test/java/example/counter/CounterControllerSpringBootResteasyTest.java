package example.counter;

import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
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
