package com.example.customermanagement;

import com.example.customermanagement.model.Customer;
import com.example.customermanagement.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AppRunner implements ApplicationRunner {
    private final CustomerRepository customerRepository;

    public AppRunner(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Customer customer = new Customer("John", "097777666", "alb@abc.com", "Some Company", "Some Address", 1990, (long) 9000000);
        customerRepository.save(customer);
    }
}
