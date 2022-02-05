package com.retailer.repository;

import com.retailer.model.Customer;
import com.retailer.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    List<Transaction> findByCustomerAndDateBetween(Customer customer, Date from, Date to);

    List<Transaction> findAllByCustomer(Customer customer);

    Optional<Transaction> findByTransactionId(Long transactionId);
}
