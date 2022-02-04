package com.retailer.service;


import com.retailer.exceptions.InvalidCustomerException;
import com.retailer.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceTest extends BaseServiceTest {

    private final String SAMPLE_CUSTOMER_FIRST_NAME = "A";
    private final String SAMPLE_CUSTOMER_LAST_NAME = "B";
    private final Customer toAddCustomer = new Customer(SAMPLE_CUSTOMER_FIRST_NAME, SAMPLE_CUSTOMER_LAST_NAME);

    @Test(expected = InvalidCustomerException.class)
    public void addCustomerExpectExceptionTest() throws InvalidCustomerException {

        // given + when
        when(customerRepository.findCustomerByFirstNameAndLastName(SAMPLE_CUSTOMER_FIRST_NAME, SAMPLE_CUSTOMER_LAST_NAME))
                .thenReturn(Optional.of(new Customer(SAMPLE_CUSTOMER_FIRST_NAME, SAMPLE_CUSTOMER_LAST_NAME)));

        // then
        customerService = new CustomerService(customerRepository);
        customerService.addCustomer(toAddCustomer);
    }

    @Test
    public void addCustomerExpectNoErrorsTest() throws InvalidCustomerException {

        // given + when
        when(customerRepository.findCustomerByFirstNameAndLastName(SAMPLE_CUSTOMER_FIRST_NAME, SAMPLE_CUSTOMER_LAST_NAME))
                .thenReturn(Optional.empty());

        when(customerRepository.save(any(Customer.class)))
                .thenReturn(toAddCustomer);

        customerService = new CustomerService(customerRepository);

        // then
        Customer result = customerService.addCustomer(toAddCustomer);
        assertEquals(result, toAddCustomer);
    }

}
