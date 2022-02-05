package com.retailer.service;


import com.google.common.collect.Lists;
import com.retailer.exceptions.InvalidCustomerException;
import com.retailer.model.Customer;
import com.retailer.repository.CustomerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer findByName(String firstName, String lastName) throws InvalidCustomerException {
        Optional<Customer> customer = customerRepository.findCustomerByFirstNameAndLastName(firstName, lastName);
        if (!customer.isPresent()) {
            throw new InvalidCustomerException("could not find customer :" + firstName + " " + lastName);
        } else {
            return customer.get();
        }
    }

    @Transactional
    public Customer addCustomer(Customer customer) throws InvalidCustomerException {
        if (customerRepository.findCustomerByFirstNameAndLastName(customer.getFirstName(), customer.getLastName()).isPresent()) {
            throw new InvalidCustomerException(customer.toString() + " already exists");
        }
        logger.info(customer.toString() + "has been saved");
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return Lists.newArrayList(customerRepository.findAll());
    }
}
