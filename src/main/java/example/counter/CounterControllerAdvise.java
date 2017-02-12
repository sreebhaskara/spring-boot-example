package example.counter;

import static java.util.Collections.singletonMap;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CounterControllerAdvise  {
    @ExceptionHandler(Throwable.class)
    ResponseEntity<Map<String,String>> onException(Throwable t) {
        
        log.error("We got an exception: " + t);

        if(t instanceof HttpStatusCodeException) {
            HttpStatusCodeException status = (HttpStatusCodeException) t;
            return ResponseEntity.status(status.getStatusCode())
                    .body(singletonMap("message", status.getStatusText()));
        }

        log.error("Request processing failed", t);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(singletonMap("message", "internal_error"));
    }
}
