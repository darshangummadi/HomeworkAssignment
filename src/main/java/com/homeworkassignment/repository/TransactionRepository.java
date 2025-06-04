package com.homeworkassignment.repository;

import com.homeworkassignment.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository {
    List<Transaction> findByCustomerIdAndDateRange(Long customerId, LocalDate startDate, LocalDate endDate);
}
