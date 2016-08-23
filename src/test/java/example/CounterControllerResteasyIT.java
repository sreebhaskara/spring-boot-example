package example;

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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:8080") // port to start server on
public class CounterControllerResteasyIT {
    @Value("${local.server.port}")
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
                    .param("int1", Integer.valueOf(1))
                    .param("int2", Integer.valueOf(2))
                .when()
                    .post("/add")
                .then()
                    .log().ifValidationFails()
                    .statusCode(200)
                    .content("value",equalTo(3));
    }
}
