package com.homeworkassignment.Helper;
import java.util.List;
public class RewardResponse {
    private Long customerId;
    private List<MonthlyReward> monthlyRewards;
    private int totalRewards;

    public RewardResponse(Long customerId, List<MonthlyReward> monthlyRewards, int totalRewards) {
        this.customerId = customerId;
        this.monthlyRewards = monthlyRewards;
        this.totalRewards = totalRewards;
    }

    public Long getCustomerId() { return customerId; }
    public List<MonthlyReward> getMonthlyRewards() { return monthlyRewards; }
    public int getTotalRewards() { return totalRewards; }
}