package com.homeworkassignment.model;

import java.time.LocalDate;

public class TransactionDetail {
    private Long transactionId;
    private LocalDate date;
    private double amount;
    private int rewardPoints;

    public TransactionDetail(Long transactionId, LocalDate date, double amount, int rewardPoints) {
        this.transactionId = transactionId;
        this.date = date;
        this.amount = amount;
        this.rewardPoints = rewardPoints;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }
}
