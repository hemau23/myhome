package com.analytique.transformer;

import com.analytique.entity.Customer;
import com.analytique.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.GenericTransformer;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by hemant on 9/7/2015.
 */
@Component
public class FileTransformer implements GenericTransformer<File,String> {

    @Autowired
    CustomerRepository repository;
    @Override
    public String transform(File source) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(source));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] str = line.split(",");
                Customer customer= new Customer();
                customer.setFirstName(str[0]);
                customer.setLastName(str[1]);
                repository.save(customer);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "completed transformation";
    }
}
