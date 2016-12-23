package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import example.counter.CounterConfig;
import example.hello.HelloConfig;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication // same as @Configuration @EnableAutoConfiguration @ComponentScan
//@Configuration
@SpringBootApplication
@EnableAutoConfiguration 
@EnableSwagger2
@Import({CounterConfig.class, HelloConfig.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}