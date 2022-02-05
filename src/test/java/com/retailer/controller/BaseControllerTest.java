package com.retailer.controller;

import com.retailer.model.Customer;
import com.retailer.model.Transaction;
import com.retailer.repository.TransactionRepository;
import com.retailer.service.CustomerService;
import com.retailer.service.TransactionService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
public class BaseControllerTest {

    @MockBean
    protected CustomerService customerService;

    @MockBean
    protected TransactionService transactionService;

    @MockBean
    protected TransactionRepository mockTransactionRepository;

    protected final Customer mockCustomer1 = new Customer("A", "B");
    protected final Customer mockCustomer2 = new Customer("C", "D");
    protected final Transaction mockTransaction1 = new Transaction(100, mockCustomer1, Date.valueOf(LocalDate.of(2000, 12, 12)));


}
