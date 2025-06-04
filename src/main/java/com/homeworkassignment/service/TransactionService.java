package com.homeworkassignment.service;

import com.homeworkassignment.model.MonthlyReward;
import com.homeworkassignment.model.Transaction;
import com.homeworkassignment.model.RewardResponse;
import com.homeworkassignment.model.TransactionDetail;
import com.homeworkassignment.repository.TransactionRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final RewardService rewardService;
    private final TransactionRepository transactionRepository;

    public TransactionService(RewardService rewardService, TransactionRepository transactionRepository) {
        this.rewardService = rewardService;
        this.transactionRepository = transactionRepository;
    }

    //@Async
    public CompletableFuture<List<Transaction>> getTransactions(Long customerId, LocalDate startDate, LocalDate endDate) {
        List<Transaction> result = transactionRepository.findByCustomerIdAndDateRange(customerId, startDate, endDate);
        return CompletableFuture.completedFuture(result);
    }

    public RewardResponse calculateRewards(Long customerId, List<Transaction> transactions) {

        Map<YearMonth, Integer> monthlyPoints = transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> YearMonth.from(t.getDate()),
                        Collectors.summingInt(t -> rewardService.calculatePoints(t.getAmount()))
                ));

        List<MonthlyReward> monthlyRewards = monthlyPoints.entrySet().stream()
                .map(entry -> new MonthlyReward(entry.getKey().toString(), entry.getValue()))
                .collect(Collectors.toList());

        int totalRewards = monthlyPoints.values().stream().mapToInt(Integer::intValue).sum();

        List<TransactionDetail> transactionDetails = transactions.stream()
                .map(t -> new TransactionDetail(
                        t.getTransactionId(),
                        t.getDate(),
                        t.getAmount(),
                        rewardService.calculatePoints(t.getAmount())
                ))
                .collect(Collectors.toList());

        return new RewardResponse(customerId, monthlyRewards, totalRewards, transactionDetails);
    }
}
