package com.homeworkassignment;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.homeworkassignment.Helper.TransactionService;

@SpringBootTest
class HomeworkAssignmentApplicationTests {

	@Autowired
	private TransactionService transactionService;

	@Test
	void contextLoads() {
		// This is to check application context loads and key beans have been injected
		assertNotNull(transactionService, "TransactionService should be available in the context");
	}
}
