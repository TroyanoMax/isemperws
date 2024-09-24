package org.isemper.com.isemperws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class InstutosemperApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstutosemperApplication.class, args);
    }

}
