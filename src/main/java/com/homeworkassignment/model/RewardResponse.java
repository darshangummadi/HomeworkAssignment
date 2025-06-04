package com.homeworkassignment.model;

import java.util.List;

public class RewardResponse {
    private Long customerId;
    private List<MonthlyReward> monthlyRewards;
    private int totalRewards;
    private List<TransactionDetail> transactionDetails;

    public RewardResponse(Long customerId, List<MonthlyReward> monthlyRewards, int totalRewards, List<TransactionDetail> transactionDetails) {
        this.customerId = customerId;
        this.monthlyRewards = monthlyRewards;
        this.totalRewards = totalRewards;
        this.transactionDetails = transactionDetails;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public List<MonthlyReward> getMonthlyRewards() {
        return monthlyRewards;
    }

    public int getTotalRewards() {
        return totalRewards;
    }

    public List<TransactionDetail> getTransactionDetails() {
        return transactionDetails;
    }
}
