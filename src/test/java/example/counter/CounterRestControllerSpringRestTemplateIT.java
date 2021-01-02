package example.counter;

import com.google.common.net.HttpHeaders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CounterRestControllerSpringRestTemplateIT {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testAdd() throws Exception {
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        CounterRequest cRequest = new CounterRequest(2, 3);
        
        HttpEntity<CounterRequest> request = new HttpEntity<CounterRequest>(
                cRequest, headers);

        ResponseEntity<CounterResult> response = template.postForEntity(
                CounterRestController.ADD_URL,
                request,
                CounterResult.class);
        assertThat(response.getBody(), equalTo(new CounterResult(5)));
    }
}