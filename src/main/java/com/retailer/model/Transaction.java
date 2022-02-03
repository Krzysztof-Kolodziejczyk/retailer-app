package com.retailer.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(nullable = false)
    @NotNull(message = "price cannot be null")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "customerId")
    @NotNull(message = "customer cannot be null")
    private Customer customer;

    @Column(nullable = false)
    @NotNull(message = "date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate localDate;

    public Transaction() {
    }

    public Transaction(Integer amount, Customer customer, LocalDate localDate) {
        this.amount = amount;
        this.customer = customer;
        this.localDate = localDate;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
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
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", customer=" + customer +
                ", localDate=" + localDate +
                '}';
    }
}
