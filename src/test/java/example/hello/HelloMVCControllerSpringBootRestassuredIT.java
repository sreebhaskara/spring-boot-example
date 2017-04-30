package example.hello;

import example.counter.CounterMVCController;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

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
                    .header(new Header("User-Agent","Rest-Assured"))
                    .log().ifValidationFails()
                .when()
                    .get(HelloMVCController.HELLO_VIEW_URL)
                .then()
                    .log().ifValidationFails()
                    .statusCode(200)
                    .body("html.head.title",
                        equalTo("Spring Boot Thymeleaf Hello World Example"))
                    .body("html.body.div.div.h2.span",
                        equalTo("Hello, Rest-Assured!") )
        ;
    }
}
