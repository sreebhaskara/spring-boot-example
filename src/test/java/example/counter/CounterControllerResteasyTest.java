package example.counter;

import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

import io.restassured.module.mockmvc.RestAssuredMockMvc;

public class CounterControllerResteasyTest {

    @Test
    public void testRequest() {
        RestAssuredMockMvc
                .given()
                    .standaloneSetup(new CounterController())
                    .log().ifValidationFails()
                    .param("int1", Integer.valueOf(1))
                    .param("int2", Integer.valueOf(2))
                .when()
                    .async()
                    .post("/add")
                .then()
                    .log().ifValidationFails()
                    .statusCode(200)
                    .content("value",equalTo(3));
    }
}
