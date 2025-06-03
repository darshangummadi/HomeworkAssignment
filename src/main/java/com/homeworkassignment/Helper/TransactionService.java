package com.homeworkassignment.Helper;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final List<Transaction> transactions = Arrays.asList(
            new Transaction(1L, 101L, 120.0, LocalDate.of(2023, 1, 5)),
            new Transaction(2L, 101L, 80.0, LocalDate.of(2023, 1, 15)),
            new Transaction(3L, 101L, 150.0, LocalDate.of(2023, 2, 10)),
            new Transaction(4L, 101L, 40.0, LocalDate.of(2023, 3, 12)),
            new Transaction(5L, 102L, 90.0, LocalDate.of(2023, 1, 20)),
            new Transaction(6L, 102L, 110.0, LocalDate.of(2023, 2, 25))
    );

    @Async
    public CompletableFuture<List<Transaction>> getTransactions(Long customerId, LocalDate startDate, LocalDate endDate) {
        List<Transaction> result = transactions.stream()
                .filter(t -> t.getCustomerId().equals(customerId)
                        && !t.getDate().isBefore(startDate)
                        && !t.getDate().isAfter(endDate))
                .collect(Collectors.toList());
        return CompletableFuture.completedFuture(result);
    }

    public RewardResponse calculateRewards(Long customerId, List<Transaction> transactions) {
        Map<YearMonth, Integer> monthlyPoints = transactions.stream()
                .collect(Collectors.groupingBy(
                        t -> YearMonth.from(t.getDate()),
                        Collectors.summingInt(t -> RewardCalculator.calculatePoints(t.getAmount()))
                ));

        List<MonthlyReward> monthlyRewards = monthlyPoints.entrySet().stream()
                .map(entry -> new MonthlyReward(entry.getKey().toString(), entry.getValue()))
                .collect(Collectors.toList());

        int totalRewards = monthlyPoints.values().stream().mapToInt(Integer::intValue).sum();

        return new RewardResponse(customerId, monthlyRewards, totalRewards);
    }
}
