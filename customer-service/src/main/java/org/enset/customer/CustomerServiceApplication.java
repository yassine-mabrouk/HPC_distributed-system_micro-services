package org.enset.customer;

import org.enset.customer.dao.CustomerRepo;
import org.enset.customer.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerServiceApplication  implements CommandLineRunner {
     @Autowired
    CustomerRepo customerRepo;
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
           for (int i=1;i<5;i++){
               Customer customer = new Customer();
               customer.setName("Customer "+i);
               customer.setEmail("Customer"+i+"@gmail.com");
               customerRepo.save(customer);
           }
    }
}
