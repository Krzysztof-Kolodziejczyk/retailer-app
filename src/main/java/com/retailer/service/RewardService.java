package com.retailer.service;

import com.retailer.exceptions.InvalidCustomerException;
import com.retailer.model.Customer;
import com.retailer.model.Transaction;
import com.retailer.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class RewardService {

    private final static int DOUBLE_POINTS_RATIO = 2;
    private final static int SINGLE_POINTS_THRESHOLD = 50;
    private final static int DOUEBLE_POINTS_THRESHOLD = 100;

    private final CustomerService customerService;
    private final TransactionRepository transactionRepository;

    public RewardService(CustomerService customerService, TransactionRepository transactionRepository) {
        this.customerService = customerService;
        this.transactionRepository = transactionRepository;
    }

    public Integer calculateMonthlyReward(String firstName, String lastName, Integer year, Integer month) throws InvalidCustomerException {
        Customer customer = customerService.findByName(firstName, lastName);
        LocalDate from = LocalDate.of(year, month, 1);
        LocalDate to = from.plusDays(from.lengthOfMonth());
        return calculateRewardForList(transactionRepository.findByCustomerAndDateBetween(customer, Date.valueOf(from), Date.valueOf(to)));
    }

    public Integer calculateTotalReward(String firstName, String lastName) throws InvalidCustomerException {
        return calculateRewardForList(transactionRepository.findAllByCustomer(customerService.findByName(firstName, lastName)));
    }

    private Integer calculateRewardForList(List<Transaction> transactions) {
        return transactions.stream().map(this::calculateReward).reduce(0, Integer::sum);
    }

    private Integer calculateReward(Transaction transaction) {
        int price = transaction.getAmount();
        if (price <= DOUEBLE_POINTS_THRESHOLD) {
            return Math.max(price - SINGLE_POINTS_THRESHOLD, 0);
        } else {
            return SINGLE_POINTS_THRESHOLD + (price - DOUEBLE_POINTS_THRESHOLD) * DOUBLE_POINTS_RATIO;
        }
    }

}
