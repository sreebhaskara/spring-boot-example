package example.hello;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HelloMVCControllerSpringBootRestassuredIT {
    @LocalServerPort
    private int serverPort;

    @Before
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
                    .get("/")
                .then()
                    .log().ifValidationFails()
                    .statusCode(200)
                    .body("**.findAll { it.@class == 'form-control' }[0].@placeholder",
                            equalTo("First number") )
                    .body("**.findAll { it.@class == 'form-control' }[1].@placeholder",
                        equalTo("Second number") )
        ;
    }
}
