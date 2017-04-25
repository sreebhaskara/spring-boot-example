package example.hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfig {
    @Bean
    public HelloMVCController helloMVCController() {
        return new HelloMVCController();
    }
    @Bean
    public HelloRestController helloRestController() {
        return new HelloRestController();
    }
}
