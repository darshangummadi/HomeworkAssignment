package com.homeworkassignment.Helper;
import java.time.LocalDate;

public class Transaction {
    private Long id;
    private Long customerId;
    private double amount;
    private LocalDate date;

    // Constructor
    public Transaction(Long id, Long customerId, double amount, LocalDate date) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
        this.date = date;
    }

    // Getters
    public Long getId() { return id; }
    public Long getCustomerId() { return customerId; }
    public double getAmount() { return amount; }
    public LocalDate getDate() { return date; }
}