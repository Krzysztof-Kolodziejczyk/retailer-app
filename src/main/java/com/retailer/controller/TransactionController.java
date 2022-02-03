package com.retailer.controller;

import com.retailer.exceptions.InvalidCustomerException;
import com.retailer.exceptions.InvalidTransactionException;
import com.retailer.model.Transaction;
import com.retailer.service.TransactionService;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createTransaction(@Valid @RequestBody Transaction transaction) throws InvalidCustomerException {
        logger.info("creating " + transaction.toString());
        return transactionService.addTransaction(transaction);
    }

    @GetMapping
    List<Transaction> getAllTransactions() {
        return transactionService.getAlltransactions();
    }

    @PutMapping()
    public Transaction updateTransaction(@RequestBody Transaction toUpdate)
            throws InvalidCustomerException, InvalidTransactionException {
        logger.info("updating transaction with id " + toUpdate.getTransactionId() + " to " + toUpdate.toString());
        return transactionService.updateTransaction(toUpdate);
    }
}
