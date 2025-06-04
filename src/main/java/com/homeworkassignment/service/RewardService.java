package com.homeworkassignment.service;

import org.springframework.stereotype.Service;

@Service
public class RewardService {

    public int calculatePoints(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }

        int dollars = (int) Math.floor(amount);
        int lowerTierPoints = Math.max(0, Math.min(dollars, 100) - 50);
        int higherTierPoints = Math.max(0, dollars - 100) * 2;

        return lowerTierPoints + higherTierPoints;
    }
}
