package com.retailer.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.retailer.model.Customer;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class TransactionRequest {

    @Column(nullable = false)
    @NotNull(message = "price cannot be null")
    @Min(value = 1L, message = "The value must be positive")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "customerId")
    @NotNull(message = "customer cannot be null")
    private Customer customer;

    @Column(nullable = false)
    @NotNull(message = "date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    public TransactionRequest() {
    }

    public TransactionRequest(int amount, Customer customer, LocalDate localDate) {
        this.amount = amount;
        this.customer = customer;
        this.localDate = localDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "amount=" + amount +
                ", customer=" + customer +
                ", localDate=" + localDate +
                '}';
    }
}
