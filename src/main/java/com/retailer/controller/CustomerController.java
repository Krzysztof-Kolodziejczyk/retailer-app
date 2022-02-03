package com.retailer.controller;

import com.retailer.exceptions.InvalidCustomerException;
import com.retailer.model.Customer;
import com.retailer.service.CustomerService;
import com.retailer.service.TransactionService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@Valid @RequestBody Customer customer) throws InvalidCustomerException {
        logger.info("adding " + customer.toString());
        return customerService.addCustomer(customer);
    }

    @GetMapping
    public List<Customer> findAllCustomers() {
        logger.info("printing all customers");
        return customerService.getAllCustomers();
    }
}
