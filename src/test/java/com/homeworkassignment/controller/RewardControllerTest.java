package com.homeworkassignment.controller;

import com.homeworkassignment.model.MonthlyReward;
import com.homeworkassignment.model.RewardResponse;
import com.homeworkassignment.model.Transaction;
import com.homeworkassignment.model.TransactionDetail;
import com.homeworkassignment.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;

@WebMvcTest(RewardController.class)
public class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        Mockito.reset(transactionService);
    }

    @Test
    void testValidRequestReturnsRewards() throws Exception {
        List<MonthlyReward> rewards = Arrays.asList(new MonthlyReward("2023-01", 90));
        List<TransactionDetail> transactions = Arrays.asList(
                new TransactionDetail(1L, LocalDate.of(2023, 1, 5), 120.0, 90)
        );
        RewardResponse response = new RewardResponse(101L, rewards, 90, transactions);

        Mockito.when(transactionService.getTransactions(eq(101L), any(), any()))
                .thenReturn(CompletableFuture.completedFuture(Arrays.asList(
                        new Transaction(1L, 101L, 120.0, LocalDate.of(2023, 1, 10))
                )));
        Mockito.when(transactionService.calculateRewards(eq(101L), any()))
                .thenReturn(response);

        MvcResult mvcResult = mockMvc.perform(get("/api/rewards")
                        .param("customerId", "101")
                        .param("startDate", "2023-01-01")
                        .param("endDate", "2023-03-31")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(101))
                .andExpect(jsonPath("$.totalRewards").value(90));
    }

    @Test
    void testNoContentScenario() throws Exception {
        Mockito.when(transactionService.getTransactions(eq(999L), any(), any()))
                .thenReturn(CompletableFuture.completedFuture(Collections.emptyList()));

        MvcResult mvcResult = mockMvc.perform(get("/api/rewards")
                        .param("customerId", "999")
                        .param("startDate", "2023-01-01")
                        .param("endDate", "2023-03-31"))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isNoContent());
    }

    @Test
    void testInvalidCustomerId() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/rewards")
                        .param("customerId", "0"))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testStartDateAfterEndDate() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/rewards")
                        .param("customerId", "101")
                        .param("startDate", "2023-04-01")
                        .param("endDate", "2023-01-01"))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testInternalServerError() throws Exception {
        CompletableFuture<List<Transaction>> failedFuture = new CompletableFuture<>();
        failedFuture.completeExceptionally(new RuntimeException("Simulated failure"));

        Mockito.when(transactionService.getTransactions(eq(101L), any(), any()))
                .thenReturn(failedFuture);

        MvcResult mvcResult = mockMvc.perform(get("/api/rewards")
                        .param("customerId", "101"))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isInternalServerError());
    }
}
