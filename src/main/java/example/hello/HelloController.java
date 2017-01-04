package example.hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public class HelloController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping(value = "/500/**")
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalError() {
        return "Internal error from Spring Boot!";
    }

    @RequestMapping(value = "/400/**")
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public String badRequest() {
        return "Bad request, says Spring Boot!";
    }
}