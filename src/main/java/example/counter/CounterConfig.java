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
    public CounterController counterController(CounterService counterService) {
        return new CounterController(counterService);
    }
}
