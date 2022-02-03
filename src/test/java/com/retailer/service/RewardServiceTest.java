package com.retailer.service;

import com.google.common.collect.ImmutableList;
import com.retailer.exceptions.InvalidCustomerException;
import com.retailer.model.Customer;
import com.retailer.model.Transaction;
import com.retailer.repository.TransactionRepository;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RewardServiceTest extends BaseServiceTest {

    TransactionRepository transactionRepository = mock(TransactionRepository.class);

    @Test
    void testForMultipleTransactionForTheSameMonth() throws InvalidCustomerException {
        //given
        List<Transaction> transactions = (ImmutableList.of(
                new Transaction(100, customer, LocalDate.of(2021, 6, 1)),
                new Transaction(200, customer, LocalDate.of(2021, 6, 2)),
                new Transaction(60, customer, LocalDate.of(2021, 6, 5)),
                new Transaction(30, customer, LocalDate.of(2021, 6, 7))
        ));

        //when
        when(transactionRepository.findByCustomerAndLocalDateBetween(any(Customer.class), any(LocalDate.class), any(LocalDate.class))).thenReturn(transactions);
        RewardService rewardService = new RewardService(customerService, transactionRepository);
        Integer result = rewardService.calculateMonthlyReward(FIRST_NAME, LAST_NAME, 2021, 04);

        //then
        Integer expected = 310;
        assertEquals(expected, result);
    }

    @Test
    void testTotalRewardForMultipleTransactions() throws InvalidCustomerException {
        //given
        List<Transaction> transactions = (ImmutableList.of(
                new Transaction(200, customer, LocalDate.of(2021, 1, 1)),
                new Transaction(300, customer, LocalDate.of(2022, 2, 2)),
                new Transaction(80, customer, LocalDate.of(2023, 3, 5)),
                new Transaction(33, customer, LocalDate.of(2024, 4, 7))
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
