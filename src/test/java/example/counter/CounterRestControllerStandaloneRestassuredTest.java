package example.counter;

import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

public class CounterRestControllerStandaloneRestassuredTest {

    @Test
    public void testRequest() {
        RestAssuredMockMvc
                .given()
                    .standaloneSetup(new CounterRestController(new CounterService()),
                            new CounterControllerAdvise())
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

    @Test
    public void testGETRequest() {
        RestAssuredMockMvc
                .given()
                .standaloneSetup(new CounterRestController(new CounterService()),
                        new CounterControllerAdvise())
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\"int1\":1, \"int2\":2}")
                .log().ifValidationFails()
                .when()
                .get(CounterRestController.ADD_URL)
                .then()
                .log().ifValidationFails()
                .statusCode(SC_METHOD_NOT_ALLOWED);
    }
}
