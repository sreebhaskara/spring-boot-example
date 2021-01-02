package example.hello;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
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
public class HelloMVCControllerSpringBootRestassuredIT {
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
                    .header(new Header("User-Agent","Rest-Assured"))
                    .log().ifValidationFails()
                .when()
                    .get(HelloMVCController.HELLO_VIEW_URL)
                .then()
                    .log().ifValidationFails()
                    .statusCode(SC_OK)
                    .body("html.head.title",
                        equalTo("Greetings"))
                    .body("html.body.div.div.h2.span",
                        equalTo("Hello, Rest-Assured!") )
        ;
    }
}
