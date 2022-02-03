package com.retailer.service;

import com.google.common.collect.Lists;
import com.retailer.exceptions.InvalidCustomerException;
import com.retailer.exceptions.InvalidTransactionException;
import com.retailer.model.Customer;
import com.retailer.model.Transaction;
import com.retailer.repository.TransactionRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(TransactionService.class);

    private final CustomerService customerService;
    private final TransactionRepository transactionRepository;

    public TransactionService(CustomerService customerService, TransactionRepository transactionRepository) {
        this.customerService = customerService;
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAlltransactions() {
        return Lists.newArrayList(transactionRepository.findAll());
    }

    @Transactional
    public Transaction addTransaction(Transaction transaction) throws InvalidCustomerException {
        transaction.setCustomer(customerService.findByName(transaction.getCustomer().getFirstName(), transaction.getCustomer().getLastName()));
        logger.info(transaction.toString() + "has been saved");
        return transactionRepository.save(transaction);
    }

    @Transactional
    public Transaction updateTransaction(Transaction toUpdate) throws InvalidCustomerException, InvalidTransactionException {
        Optional<Transaction> transaction = transactionRepository.findByTransactionId(toUpdate.getTransactionId());
        if(transaction.isPresent()){
            Customer customer = toUpdate.getCustomer();
            transaction.get().setCustomer(customerService.findByName(customer.getFirstName(), customer.getLastName()));
            transaction.get().setAmount(toUpdate.getAmount());
            transaction.get().setLocalDate(toUpdate.getLocalDate());
            return transaction.get();
        }else {
            throw new InvalidTransactionException("Could not find Transacion for " + toUpdate.getTransactionId() + " id.");
        }
    }
}
