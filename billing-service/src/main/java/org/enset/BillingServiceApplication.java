package org.enset;

import org.enset.entitis.Bill;
import org.enset.entitis.Customer;
import org.enset.entitis.Product;
import org.enset.entitis.ProductItem;
import org.enset.repositories.BillRepository;
import org.enset.repositories.ProductItemRepository;
import org.enset.service.CustomerRestClient;
import org.enset.service.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.hateoas.PagedModel;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }
   @Bean
    CommandLineRunner start(
            BillRepository billRepository,
            CustomerRestClient customerRestClient,
            ProductRestClient productRestClient,
            ProductItemRepository productItemRepository,
            RepositoryRestConfiguration config
    ){
        config.exposeIdsFor(Bill.class);
        return args -> {
            Customer customer=customerRestClient.getCustomerById(1L);
            Bill bill =new Bill();
            bill.setBillingDate(new Date());
            bill.setCustomerID(customer.getId());
            billRepository.save(bill);
            PagedModel<Product> productPagedModel=productRestClient.getAllProducts();
            productPagedModel.forEach(product-> {
               ProductItem productItem=new ProductItem();
               productItem.setProductID(product.getId());
               productItem.setBill(bill);
               productItem.setPrice(product.getPrice());
               productItem.setQuantity(1+new Random().nextInt(99));
               productItemRepository.save(productItem);
            });
        };
    }
}
