package org.enset.customer.dao;

import org.enset.customer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRepo  extends JpaRepository<Customer,Long> {
}
