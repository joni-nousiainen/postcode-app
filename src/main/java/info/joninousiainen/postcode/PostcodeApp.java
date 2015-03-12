package info.joninousiainen.postcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class PostcodeApp {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(PostcodeApp.class, args);
    }
}
