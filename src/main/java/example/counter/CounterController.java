package example.counter;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import io.swagger.annotations.ApiOperation;

@RestController
public class CounterController {
    @RequestMapping(value = "/add",
            method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "add two numbers together", response = CounterResult.class)
    public DeferredResult<CounterResult> add(
            @RequestParam(required = true) Integer int1,
            @RequestParam(required = true) Integer int2) {
        DeferredResult<CounterResult> result = new DeferredResult<>();
        result.setResult(new CounterResult(int1.intValue() + int2.intValue()));
        result.setErrorResult(new CounterErrorResult("Couldn't perform operation"));
        return result;
    }
}
