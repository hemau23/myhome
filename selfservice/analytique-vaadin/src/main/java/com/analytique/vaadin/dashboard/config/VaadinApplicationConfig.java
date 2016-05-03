package com.analytique.vaadin.dashboard.config;


import com.analytique.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;

/*@EnableAutoConfiguration
@Configuration
@ComponentScan("com.vaadin")*/


public class VaadinApplicationConfig extends ApplicationConfig {

    protected VaadinApplicationConfig() {
    }

    public static void main(String[] args) {
        SpringApplication.run(VaadinApplicationConfig.class, args);
    }
}
