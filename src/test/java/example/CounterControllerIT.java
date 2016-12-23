package example;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.net.HttpHeaders;

import example.counter.CounterResult;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CounterControllerIT {

    @Value("${local.server.port}")
    private int port;

    private URL base;
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/add");
        template = new TestRestTemplate();
    }

    // for Jackson deserialization - Jackson needs its helper annotation to be able
    // to use the constructor
    static class MyCounterResult extends CounterResult {
        public MyCounterResult(@JsonProperty("value") int value) {
            super(value);
        }
    }

    @Test
    public void testAdd() throws Exception {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON.toString());
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.toString());

        String body = "{\"int1\":2, \"int2\":3}";
        
        HttpEntity<String> request = new HttpEntity<String>(
                body, headers);

        ResponseEntity<MyCounterResult> response = template.postForEntity(base.toString(), request,
                MyCounterResult.class);
        assertThat(response.getBody(), equalTo(new MyCounterResult(5)));
    }
}