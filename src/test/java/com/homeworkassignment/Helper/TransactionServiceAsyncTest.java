package com.homeworkassignment.Helper;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceAsyncTest {

    private final TransactionService transactionService = new TransactionService();

    @Test
    void getTransactions_returnsExpectedResults() throws Exception {

        Long customerId = 101L;
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 3, 31);

        CompletableFuture<List<Transaction>> futureTransactions =
                transactionService.getTransactions(customerId, startDate, endDate);


        List<Transaction> transactions = futureTransactions.get();
        assertFalse(transactions.isEmpty(), "Expected some transactions for the given date range");
    }
}
