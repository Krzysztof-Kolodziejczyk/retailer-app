package com.retailer.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.retailer.model.Customer;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    @NotNull(message = "price cannot be null")
    @Min(value = 1L, message = "The value must be positive")
    private int amount;

    @NotNull(message = "customer cannot be null")
    private Customer customer;

    @NotNull(message = "date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(dataType = "java.sql.Date")
    private Date date;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "TransactionRequest{" +
                "amount=" + amount +
                ", customer=" + customer +
                ", localDate=" + date +
                '}';
    }
}
