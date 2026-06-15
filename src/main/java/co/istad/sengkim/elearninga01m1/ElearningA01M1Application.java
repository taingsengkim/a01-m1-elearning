package co.istad.sengkim.elearninga01m1;

import jakarta.persistence.EntityListeners;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ElearningA01M1Application {

    public static void main(String[] args) {
        SpringApplication.run(ElearningA01M1Application.class, args);
    }

}
