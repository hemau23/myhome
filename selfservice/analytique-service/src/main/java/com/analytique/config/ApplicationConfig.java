package com.analytique.config;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.vaadin.spring.annotation.EnableVaadinExtensions;

@SpringBootApplication
@ComponentScan(basePackages = {"com.analytique"})
@EnableMongoRepositories(basePackages = { "com.analytique" })
@EnableTransactionManagement
@EnableSpringDataWebSupport
@EnableVaadinExtensions

public class ApplicationConfig {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(ApplicationConfig.class, args);
        
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

}
