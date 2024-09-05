package org.isemper.com.institutosemper.config;

import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

@Configuration
public class TestPostConstruct {

    @PostConstruct
    public void testInit() {
        System.out.println("-----------------------Método testInit ejecutado------------------------");
    }
}
