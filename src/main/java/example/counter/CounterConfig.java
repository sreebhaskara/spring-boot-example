package example.counter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterConfig {
    @Bean // using counterService name here would be overridden by spring boot metrics
    public CounterService myCounterService() {
        return new CounterService();
    }
    @Bean
    public CounterRestController counterRestController(CounterService counterService) {
        return new CounterRestController(counterService);
    }
    @Bean
    public CounterMVCController counterMVCController() {
        return new CounterMVCController();
    }
}
