package com.homeworkassignment.controller;

import com.homeworkassignment.service.TransactionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final TransactionService transactionService;

    public RewardController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<?>> getRewards(
            @RequestParam Long customerId,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {


        if (startDate == null) startDate = LocalDate.now().minusMonths(3);
        if (endDate == null) endDate = LocalDate.now();


        if (customerId == null || customerId <= 0) {
            return CompletableFuture.completedFuture(
                    ResponseEntity.status(400).body("Invalid customer ID"));
        }

        if (startDate.isAfter(endDate)) {
            return CompletableFuture.completedFuture(
                    ResponseEntity.status(400).body("Start date must be before end date"));
        }


        return transactionService.getTransactions(customerId, startDate, endDate)
                .thenApply(trans -> {
                    if (trans.isEmpty()) {
                        return ResponseEntity.noContent().build();
                    }
                    return ResponseEntity.ok(transactionService.calculateRewards(customerId, trans));
                })
                .exceptionally(ex ->
                        ResponseEntity.status(500).body("Internal server error")
                );
    }
}
