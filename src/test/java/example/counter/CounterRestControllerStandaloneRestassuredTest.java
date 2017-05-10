package example.counter;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

public class CounterRestControllerStandaloneRestassuredTest {

    @Test
    public void testRequest() {
        RestAssuredMockMvc
                .given()
                    .standaloneSetup(new CounterRestController(new CounterService()))
                    .log().ifValidationFails()
                    .contentType(ContentType.JSON)
                    .body("{\"int1\":1, \"int2\":2}")
                .when()
                    .async()
                    .post(CounterRestController.ADD_URL)
                .then()
                    .log().ifValidationFails()
                    .statusCode(SC_OK)
                    .content("value",equalTo(3));
    }
}
