package example;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.net.HttpHeaders;

import example.Application;
import example.counter.CounterResult;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class CounterControllerIT {

    @Value("${local.server.port}")
    private int port;

    private URL base;
    private RestTemplate template;

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
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE.toString());

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("int1", "2");
        body.add("int2", "3");
        
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<MultiValueMap<String,String>>(
                body, headers);

        ResponseEntity<MyCounterResult> response = template.postForEntity(base.toString(), request,
                MyCounterResult.class);
        assertThat(response.getBody(), equalTo(new MyCounterResult(5)));
    }
}