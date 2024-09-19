package org.isemper.com.institutosemper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAwareImpl")
public class InstutosemperApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstutosemperApplication.class, args);
    }

}
