package com.retailer.service;

import com.retailer.model.Customer;
import com.retailer.model.Transaction;
import com.retailer.api.request.TransactionRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;

@RunWith(SpringJUnit4ClassRunner.class)
public class TransactionServiceTest extends BaseServiceTest {

    @Test
    public void updateTransactionTest() throws Exception {

        // given
        Customer customer1 = new Customer("A", "B");
        Customer customer2 = new Customer("C", "D");
        Transaction transaction = new Transaction(200, customer1, Date.valueOf(LocalDate.of(2021, 12,12)));
        TransactionRequest transactionRequest = new TransactionRequest(100, customer2, Date.valueOf(LocalDate.of(2000, 10, 10)));
        Transaction expectedTransaction = new Transaction(100, customer2, Date.valueOf(LocalDate.of(2000,10,10)));

        TransactionService transactionService = new TransactionService(customerService, transactionRepository);

        // when
        Mockito.when(transactionRepository.findByTransactionId(anyLong()))
                .thenReturn(Optional.of(transaction));

        Mockito.when(customerService.findByName(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(customer2);

        // then
        Transaction actualResponse = transactionService.updateTransaction(anyLong(), transactionRequest);

        assertEquals(actualResponse.toString(), expectedTransaction.toString());

    }

}
