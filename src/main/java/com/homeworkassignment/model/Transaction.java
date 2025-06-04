package com.homeworkassignment.model;
import java.time.LocalDate;

public class Transaction {
    private Long TransactionId;
    private Long customerId;
    private double amount;
    private LocalDate date;

    // Constructor
    public Transaction(Long transactionId, Long customerId, double amount, LocalDate date) {
        this.TransactionId = transactionId;
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
    }

    // Getters
    public Long getTransactionId() { return TransactionId; }
    public Long getCustomerId() { return customerId; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }
}