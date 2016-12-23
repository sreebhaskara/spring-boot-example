package example.hello;

import org.springframework.context.annotation.Bean;

public class HelloConfig {
    @Bean
    public HelloController helloController() {
        return new HelloController();
    }
}
