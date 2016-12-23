package example.counter;

import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

public class CounterControllerStandaloneResteasyTest {

    @Test
    public void testRequest() {
        RestAssuredMockMvc
                .given()
                    .standaloneSetup(new CounterController(new CounterService()))
                    .log().ifValidationFails()
                    .contentType(ContentType.JSON)
                    .body("{\"int1\":1, \"int2\":2}")
                .when()
                    .async()
                    .post("/add")
                .then()
                    .log().ifValidationFails()
                    .statusCode(200)
                    .content("value",equalTo(3));
    }
}
