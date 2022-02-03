package com.retailer.repository;

import com.retailer.model.Customer;
import com.retailer.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findByCustomerAndLocalDateBetween(Customer customer, LocalDate from, LocalDate to);

    List<Transaction> findAllByCustomer(Customer customer);

    Optional<Transaction> findByTransactionId(Long transactionId);
}
