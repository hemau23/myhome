package com.analytique.hello;

import com.analytique.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.analytique.repository.CustomerRepository;

@RestController
public class HelloController {

    @Autowired
    public CustomerRepository repository;
    
    @RequestMapping("/")
    public String index() {

        Customer customer = new Customer();
        customer.setFirstName("Hemant");
        customer.setLastName("Jaiswal");
        //repository.save(customer);
        return "Greetings from Spring Boot!";
    }
    
}
