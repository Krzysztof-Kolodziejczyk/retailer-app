package com.retailer.service;

import com.google.common.collect.ImmutableList;
import com.retailer.exceptions.InvalidCustomerException;
import com.retailer.model.Customer;
import com.retailer.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class RewardServiceTest extends BaseServiceTest {

    private final String FIRST_NAME = "A";
    private final String LAST_NAME = "B";

    private final Customer customer = new Customer(FIRST_NAME, LAST_NAME);

    @Test
    public void multipleTransactionForTheSameMonthExpectNoErrosTest() throws InvalidCustomerException {
        //given
        List<Transaction> transactions = (ImmutableList.of(
                new Transaction(100, customer, Date.valueOf(LocalDate.of(2021, 6, 1))),
                new Transaction(200, customer, Date.valueOf(LocalDate.of(2021, 6, 2))),
                new Transaction(60, customer, Date.valueOf(LocalDate.of(2021, 6, 5))),
                new Transaction(30, customer, Date.valueOf(LocalDate.of(2021, 6, 7)))
        ));

        //when
        Mockito.when(customerRepository.findCustomerByFirstNameAndLastName(anyString(), anyString()))
                .thenReturn(java.util.Optional.of(customer));

        Mockito.when(transactionRepository.findByCustomerAndDateBetween(any(Customer.class), any(Date.class), any(Date.class)))
                .thenReturn(transactions);

        customerService = new CustomerService(customerRepository);

        RewardService rewardService = new RewardService(customerService, transactionRepository);
        Integer result = rewardService.calculateMonthlyReward(FIRST_NAME, LAST_NAME, 2021, 04);

        //then
        Integer expected = 310;
        assertEquals(expected, result);
    }

    @Test
    public void totalRewardForMultipleTransactionsExpectNoErrosTest() throws InvalidCustomerException {
        //given
        List<Transaction> transactions = (ImmutableList.of(
                new Transaction(200, customer, Date.valueOf(LocalDate.of(2021, 1, 1))),
                new Transaction(300, customer, Date.valueOf(LocalDate.of(2022, 2, 2))),
                new Transaction(80, customer, Date.valueOf(LocalDate.of(2023, 3, 5))),
                new Transaction(33, customer, Date.valueOf(LocalDate.of(2024, 4, 7)))
        ));

        //when
        when(transactionRepository.findAllByCustomer(any(Customer.class))).thenReturn(transactions);

        RewardService rewardService = new RewardService(customerService, transactionRepository);

        Integer result = rewardService.calculateTotalReward(FIRST_NAME, LAST_NAME);

        //then
        Integer expected = 730;
        assertEquals(expected, result);
    }

}
