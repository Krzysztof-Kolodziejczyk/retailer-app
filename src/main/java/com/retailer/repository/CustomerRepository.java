package com.retailer.repository;

import com.retailer.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Optional<Customer> findCustomerByFirstNameAndLastName(String firstName, String lastName);

}
