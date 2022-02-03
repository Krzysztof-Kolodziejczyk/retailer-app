package com.retailer.service;

import com.retailer.model.Customer;
import com.retailer.repository.CustomerRepository;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseServiceTest {

    protected final String FIRST_NAME = "A";
    protected final String LAST_NAME = "B";

    Customer customer = new Customer(FIRST_NAME, LAST_NAME);
    protected CustomerService customerService = new CustomerService(mockCustomerRepository(customer));

    private CustomerRepository mockCustomerRepository(Customer customer) {
        CustomerRepository customerRepository = mock(CustomerRepository.class);
        when(customerRepository.findCustomerByFirstNameAndLastName(anyString(), anyString())).thenReturn(java.util.Optional.ofNullable(customer));
        return customerRepository;
    }
}
