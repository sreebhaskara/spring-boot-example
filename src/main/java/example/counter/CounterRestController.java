package example.counter;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class CounterRestController {
    public static final String ADD_URL = "/counter/add";
    
    private final CounterService counterService;

    public CounterRestController(CounterService counterService) {
        this.counterService = counterService;
    }

    @RequestMapping(value = ADD_URL,
            method = RequestMethod.POST)
    @Operation(summary = "add two numbers together")
    public DeferredResult<CounterResult> add(@RequestBody @Valid
            CounterRequest counterRequest) {
        DeferredResult<CounterResult> result = new DeferredResult<>();
        result.setResult(counterService.count(counterRequest));
        return result;
    }
}
