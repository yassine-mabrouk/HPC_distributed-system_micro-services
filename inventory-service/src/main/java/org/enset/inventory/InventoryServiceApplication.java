package org.enset.inventory;

import org.enset.inventory.dao.ProductRepo;
import org.enset.inventory.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InventoryServiceApplication implements CommandLineRunner {
    @Autowired
    ProductRepo productRepo;
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
          for (int i=1;i<4;i++){
              Product p=new Product();
              p.setName("Product "+i);
              p.setPrice(i*10);
              productRepo.save(p);
          }
        System.out.println("===============Les produits enregistrés========= ");
        productRepo.findAll().forEach(c-> {
            System.out.println(c.toString());
        });
    }
}
