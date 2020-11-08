package example.counter;

import static java.util.Collections.singletonMap;

import java.util.Map;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class CounterControllerAdvise  {
    @ExceptionHandler({InvalidFormatException.class, HttpMessageNotReadableException.class})
    ResponseEntity<Map<String,String>> invalidFormat(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(singletonMap("message", e.getMessage()));
    }

    @ExceptionHandler(HttpStatusCodeException.class)
    ResponseEntity<Map<String,String>> invalidFormat(HttpStatusCodeException e) {
        return ResponseEntity.status(e.getStatusCode())
                .body(singletonMap("message", e.getStatusText()));
    }
}
