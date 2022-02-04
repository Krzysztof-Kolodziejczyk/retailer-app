package com.retailer.service;

import com.retailer.model.Customer;
import com.retailer.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionServiceTest extends BaseServiceTest {

    @Test
    public void updateTransactionTest() throws Exception {

        // given
        Customer customer = new Customer("A", "B");
        Transaction toUpdateTransaction = new Transaction(100, customer, LocalDate.of(2000, 10, 10));

        TransactionService transactionService = new TransactionService(customerService, transactionRepository);

        // when
        Mockito.when(transactionRepository.findByTransactionId(Mockito.anyLong()))
                .thenReturn(Optional.of(toUpdateTransaction));

        Mockito.when(customerService.findByName(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(customer);

        // then
        Transaction actualResponse = transactionService.updateTransaction(toUpdateTransaction);

        assertEquals(actualResponse, toUpdateTransaction);

    }

}
