package org.isemper.com.isemperws.config;

import org.springframework.context.annotation.Configuration;
import jakarta.annotation.PostConstruct;

@Configuration
public class TestPostConstruct {

    @PostConstruct
    public void testInit() {
        System.out.println("-----------------------Método testInit ejecutado------------------------");
    }
}
