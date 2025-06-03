Homework Assignment: Rewards Calculation Application
This project is a simple Spring Boot application written in Java 8 that calculates reward points for customer transactions. The application demonstrates several Java 8 features (like Lambda Expressions, Streams, and the new Date/Time API) and uses Spring Boot for building RESTful services. In addition, it includes tests (both conventional and parameterized) to ensure the logic works correctly.

Table of Contents
Overview
Project Structure
Key Components
Design and Performance Considerations
Final Notes

Overview
This application simulates a scenario where customer transactions are processed to determine reward points. Reward points are calculated using the following simple rule:
For every dollar spent over $50, you earn points.
Additional bonus points are calculated for dollars spent above $100.
The objective is to show how to use Java 8 features to process lists of transactions, calculate rewards, and expose the logic via a REST endpoint.

Project Structure
Below is a brief explanation of the main files and their roles:
HomeworkAssignmentApplication.java This is the main class to start the Spring Boot application. It contains the main method which boots up the Spring context.
TransactionService.java Contains business logic to:
Retrieve transactions for a customer within a specified date range.
Calculate monthly rewards using Java 8 streams and collectors.
Transaction.java A simple Plain Old Java Object (POJO) that represents a single transaction, including details like transaction ID, customer ID, amount, and date.
RewardResponse.java A data class used as a response model that includes the customer ID, a list of monthly rewards, and the total reward points.
RewardController.java A REST controller that exposes an endpoint (/api/rewards) for clients to request reward calculations using customer ID, start date, and end date as parameters.
RewardCalculator.java Contains the static method to compute reward points based on a given transaction amount. The calculation logic is split into lower-tier and higher-tier components for numbers between $50–$100 and beyond $100 respectively.
MonthlyReward.java A simple class representing the reward for a given month. It stores the month (as a String) and the points earned.
Test Classes:
HomeworkAssignmentApplicationTests.java ensures that the Spring Application context loads properly and that essential beans (like TransactionService) are correctly injected.
RewardCalculatorTest.java contains unit tests written with JUnit 5, including both standard and parameterized tests, to check the correctness of the reward calculation logic.

Key Components
1. Java 8 Features
Lambda Expressions and Streams
Date/Time API
2. Asynchronous Processing
Homework Assignment: Rewards Calculation Application
This project is a simple Spring Boot application written in Java 8 that calculates reward points for customer transactions. The application demonstrates several Java 8 features (like Lambda Expressions, Streams, and the new Date/Time API) and uses Spring Boot for building RESTful services. In addition, it includes tests (both conventional and parameterized) to ensure the logic works correctly.

Table of Contents
Overview

Project Structure

Key Components

How to Build and Run

Testing

Design and Performance Considerations

Final Notes

Overview
This application simulates a scenario where customer transactions are processed to determine reward points. Reward points are calculated using the following simple rule:

For every dollar spent over $50, you earn points.

Additional bonus points are calculated for dollars spent above $100.

The objective is to show how to use Java 8 features to process lists of transactions, calculate rewards, and expose the logic via a REST endpoint.

Project Structure
Below is a brief explanation of the main files and their roles:

HomeworkAssignmentApplication.java This is the main class to start the Spring Boot application. It contains the main method which boots up the Spring context.

TransactionService.java Contains business logic to:

Retrieve transactions for a customer within a specified date range.

Calculate monthly rewards using Java 8 streams and collectors.

Transaction.java A simple Plain Old Java Object (POJO) that represents a single transaction, including details like transaction ID, customer ID, amount, and date.

RewardResponse.java A data class used as a response model that includes the customer ID, a list of monthly rewards, and the total reward points.

RewardController.java A REST controller that exposes an endpoint (/api/rewards) for clients to request reward calculations using customer ID, start date, and end date as parameters.

RewardCalculator.java Contains the static method to compute reward points based on a given transaction amount. The calculation logic is split into lower-tier and higher-tier components for numbers between $50–$100 and beyond $100 respectively.

MonthlyReward.java A simple class representing the reward for a given month. It stores the month (as a String) and the points earned.

Test Classes:

HomeworkAssignmentApplicationTests.java ensures that the Spring Application context loads properly and that essential beans (like TransactionService) are correctly injected.

RewardCalculatorTest.java contains unit tests written with JUnit 5, including both standard and parameterized tests, to check the correctness of the reward calculation logic.

**Key Components**
1. Java 8 Features
Lambda Expressions and Streams: Used in TransactionService.java to filter and group transactions. These features enable concise and readable code when processing collections.

Date/Time API: Classes such as LocalDate and YearMonth are used to manage dates and group transactions by month.

2. Asynchronous Processing
The method getTransactions in TransactionService.java uses the @Async annotation from Spring. This allows the method to run on a separate thread without blocking the main application thread. The asynchronous work is wrapped with CompletableFuture.completedFuture(…) after processing.

3. Testing Strategies
Conventional Unit Tests: Individual tests check specific boundary cases in the reward logic.

Parameterized Tests: These tests allow multiple scenarios to be tested in a single method. They demonstrate a deeper range of test cases without writing repetitive code.

**How to Build and Run**
Prerequisites

Java 8: Ensure that Java 8 is installed on your machine.
Maven: This project uses Maven for dependency management and build configuration. Make sure one of these tools is installed and properly configured.
Spring Boot Dependencies: All necessary Spring Boot dependencies are listed in your pom.xml

3. Testing Strategies
Conventional Unit Tests: Individual tests check specific boundary cases in the reward logic.
Parameterized Tests: These tests allow multiple scenarios to be tested in a single method. They demonstrate a deeper range of test cases without writing repetitive code.


**Design and Performance Considerations**
Performance
Stream Efficiency: The code uses Java 8 streams to process lists, which is efficient for collections of moderate size. For very large data sets, consider optimizing by pre-grouping or caching results.

Asynchronous Execution: The use of @Async ensures that fetching transactions does not block other operations in your application.

**Readability and Maintainability**
Clean Code: The logic is split into smaller, well-named methods and classes. For example, the reward calculation logic in RewardCalculator.java is divided into clear steps.

Testing: Extensive unit tests help catch edge cases and ensure that the logic does not break as the code evolves.

**Final Notes**
This project is designed to be a robust example for understanding:

How to use Java 8 features (streams, lambda expressions, date/time API).

How to build and run a Spring Boot application.

How to write and organize tests using JUnit 5, including parameterized tests.
