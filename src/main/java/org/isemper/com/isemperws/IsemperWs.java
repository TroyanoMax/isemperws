package org.isemper.com.isemperws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class IsemperWs {

    public static void main(String[] args) {
        SpringApplication.run(IsemperWs.class, args);
    }

}
