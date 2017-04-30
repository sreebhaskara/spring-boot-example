package example.counter;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class CounterRestController {
    public static final String ADD_URL = "/counter/add";
    
    private final CounterService counterService;

    public CounterRestController(CounterService counterService) {
        this.counterService = counterService;
    }

    @RequestMapping(value = ADD_URL,
            method = RequestMethod.POST)
    @ApiOperation(value = "add two numbers together", response = CounterResult.class)
    public DeferredResult<CounterResult> add(@RequestBody @Valid
            CounterRequest counterRequest) {
        DeferredResult<CounterResult> result = new DeferredResult<>();
        result.setResult(counterService.count(counterRequest));
        return result;
    }
}
