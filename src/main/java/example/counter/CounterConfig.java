package example.counter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CounterConfig {
    @Bean
    public CounterService counterService() {
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
