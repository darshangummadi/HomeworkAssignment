package com.homeworkassignment.service;

import com.homeworkassignment.model.MonthlyReward;
import com.homeworkassignment.model.RewardResponse;
import com.homeworkassignment.model.Transaction;
import com.homeworkassignment.model.TransactionDetail;
import com.homeworkassignment.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    private TransactionService transactionService;
    private RewardService rewardService;
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setup() {
        rewardService = mock(RewardService.class);
        transactionRepository = mock(TransactionRepository.class);
        transactionService = new TransactionService(rewardService, transactionRepository);
    }

    @Test
    void testRewardCalculationLogic() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction(1L, 101L, 120.0, LocalDate.of(2023, 1, 10)),
                new Transaction(2L, 101L, 80.0, LocalDate.of(2023, 1, 20))
        );

        when(rewardService.calculatePoints(120.0)).thenReturn(90);
        when(rewardService.calculatePoints(80.0)).thenReturn(30);

        RewardResponse response = transactionService.calculateRewards(101L, transactions);

        assertEquals(101L, response.getCustomerId());
        assertEquals(120, response.getTotalRewards());

        List<MonthlyReward> monthlyRewards = response.getMonthlyRewards();
        assertEquals(1, monthlyRewards.size());
        assertEquals("2023-01", monthlyRewards.get(0).getMonth());
        assertEquals(120, monthlyRewards.get(0).getPoints());

        List<TransactionDetail> details = response.getTransactionDetails();
        assertEquals(2, details.size());
        assertEquals(1L, details.get(0).getTransactionId());
        assertEquals(90, details.get(0).getRewardPoints());
    }

    @Test
    void testEmptyTransactions() {
        RewardResponse response = transactionService.calculateRewards(101L, Arrays.asList());

        assertEquals(101L, response.getCustomerId());
        assertEquals(0, response.getTotalRewards());
        assertTrue(response.getMonthlyRewards().isEmpty());
        assertTrue(response.getTransactionDetails().isEmpty());
    }
}
