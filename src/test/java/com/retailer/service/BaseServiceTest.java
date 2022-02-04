package com.retailer.service;

import com.retailer.repository.CustomerRepository;
import com.retailer.repository.TransactionRepository;
import org.springframework.boot.test.mock.mockito.MockBean;

public class BaseServiceTest {
    @MockBean
    protected TransactionRepository transactionRepository;

    @MockBean
    protected CustomerRepository customerRepository;

    @MockBean
    protected CustomerService customerService;
}
