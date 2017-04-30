package example.counter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(produces = "text/html")
public class CounterMVCController {
    public static final String COUNTER_VIEW_URL = "/counter";
    @RequestMapping(value = COUNTER_VIEW_URL, method = RequestMethod.GET)
    public String counterView() {
        return "counter";
    }
}
