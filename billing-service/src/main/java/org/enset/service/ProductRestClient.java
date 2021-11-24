package org.enset.service;

import org.enset.entitis.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

@FeignClient(name="INVENTORY-SERVICE")
public interface ProductRestClient {

    @GetMapping(path = "/products")
    public PagedModel<Product> getAllProducts();
    @GetMapping(path = "/products/{id}")
    public  Product getProductById(@PathVariable long id );
}
