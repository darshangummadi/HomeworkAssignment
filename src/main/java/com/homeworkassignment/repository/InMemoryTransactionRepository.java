package com.homeworkassignment.repository;

import com.homeworkassignment.model.Transaction;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemoryTransactionRepository implements TransactionRepository {

    private final List<Transaction> transactions = Arrays.asList(
            new Transaction(1L, 101L, 120.0, LocalDate.of(2023, 1, 5)),
            new Transaction(2L, 101L, 80.0, LocalDate.of(2023, 1, 15)),
            new Transaction(3L, 101L, 150.0, LocalDate.of(2023, 2, 10)),
            new Transaction(4L, 101L, 40.0, LocalDate.of(2023, 3, 12)),
            new Transaction(5L, 102L, 90.0, LocalDate.of(2023, 1, 20)),
            new Transaction(6L, 102L, 110.0, LocalDate.of(2023, 2, 25))
    );

    @Override
    public List<Transaction> findByCustomerIdAndDateRange(Long customerId, LocalDate startDate, LocalDate endDate) {
        return transactions.stream()
                .filter(t -> t.getCustomerId().equals(customerId)
                        && !t.getDate().isBefore(startDate)
                        && !t.getDate().isAfter(endDate))
                .collect(Collectors.toList());
    }
}
