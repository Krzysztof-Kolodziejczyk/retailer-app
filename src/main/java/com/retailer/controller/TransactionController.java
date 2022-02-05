package com.retailer.controller;

import com.retailer.exceptions.InvalidCustomerException;
import com.retailer.exceptions.InvalidTransactionException;
import com.retailer.model.Transaction;
import com.retailer.api.request.TransactionRequest;
import com.retailer.service.TransactionService;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody TransactionRequest request) throws InvalidCustomerException {
        logger.info("creating " + request.toString());
        return ResponseEntity.ok(transactionService.addTransaction(request));
    }

    @GetMapping
    ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAlltransactions());
    }

    @PutMapping("/{toUpdateTransactionId}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long toUpdateTransactionId, @Valid @RequestBody TransactionRequest request)
            throws InvalidCustomerException, InvalidTransactionException {
        logger.info("updating transaction with id " + toUpdateTransactionId + " to " + request.toString());
        return ResponseEntity.ok(transactionService.updateTransaction(toUpdateTransactionId, request));
    }
}
