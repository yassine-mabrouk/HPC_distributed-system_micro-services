package org.enset.controllers;

import org.enset.entitis.Bill;
import org.enset.entitis.Customer;
import org.enset.entitis.Product;
import org.enset.repositories.BillRepository;
import org.enset.repositories.ProductItemRepository;
import org.enset.service.CustomerRestClient;
import org.enset.service.ProductRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BillController {
     @Autowired
    BillRepository billRepository;
    CustomerRestClient customerRestClient;
    ProductRestClient productRestClient;
    ProductItemRepository productItemRepository;
    // Injection des dependence avec Constructeur meieux que @Autowired
    public BillController(CustomerRestClient customerRestClient, ProductRestClient productRestClient, ProductItemRepository productItemRepository) {
        this.customerRestClient = customerRestClient;
        this.productRestClient = productRestClient;
        this.productItemRepository = productItemRepository;
    }
    @GetMapping(path = "/fullBill/{id}")
    public Bill getFullBill(@PathVariable long id){
        System.out.println("---------Billing---------------");
          Bill fullBill =billRepository.findById(id).get();
          Customer  customer =customerRestClient.getCustomerById(fullBill.getCustomerID());
          fullBill.setCustomer(customer);
        fullBill.getProductItems().forEach(pi-> {
            Product product=productRestClient.getProductById(pi.getProductID());
            pi.setProduct(product);
        });

        return fullBill;
    }
}
