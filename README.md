---

## 📘 Homework Assignment: Reward Calculation API

This is a Java 8 Spring Boot application that calculates customer reward points based on transactions over a given date range. The application exposes a RESTful API and demonstrates clean architecture, testability, and Java 8 features.

---

## 🛠 Tech Stack

* Java 8
* Spring Boot 2.6.15
* Maven
* JUnit 5
* Mockito

---

## 🔁 Reward Calculation Logic

* **+1 point** for every dollar spent **between \$50–\$100**
* **+2 points** for every dollar spent **above \$100**

Example:
`$120` purchase = `50 x 1 + 20 x 2 = 90 points`

---

## 🚀 How to Run

```bash
mvn clean install
mvn spring-boot:run
```

Visit: [http://localhost:8080/api/rewards](http://localhost:8080/api/rewards)

---

## 🔗 REST API

### Endpoint

```
GET /api/rewards
```

### Query Params

| Name       | Type         | Required | Description                                    |
| ---------- | ------------ | -------- | ---------------------------------------------- |
| customerId | `Long`       | ✅        | Customer ID                                    |
| startDate  | `yyyy-MM-dd` | ❌        | Optional start date (defaults to 3 months ago) |
| endDate    | `yyyy-MM-dd` | ❌        | Optional end date (defaults to today)          |

### Example Request

```
GET /api/rewards?customerId=101&startDate=2023-01-01&endDate=2023-03-31
```

### Sample Response

```json
{
  "customerId": 101,
  "totalRewards": 120,
  "monthlyRewards": [
    { "month": "2023-01", "points": 120 }
  ],
  "transactionDetails": [
    { "transactionId": 1, "date": "2023-01-10", "amount": 120.0, "rewardPoints": 90 }
  ]
}
```

---

## ✅ Tests

Run all tests:

```bash
mvn test
```

**Coverage includes:**

* Controller tests (MockMvc)
* Service layer logic
* Edge case handling

---

## 📦 Project Structure (Main)

```
com.homeworkassignment
 ├── controller         # REST API
 ├── service            # Business logic
 ├── repository         # Data access (mock)
 ├── model              # DTOs and domain objects
 └── HomeworkAssignmentApplication.java
```

---
1. Valid Request with Rewards

<img width="1673" alt="image" src="https://github.com/user-attachments/assets/3c704f3c-6984-4f18-87aa-575de053826f" />

```json
{
    "customerId": 101,
    "monthlyRewards": [
        {
            "month": "2023-03",
            "points": 0
        },
        {
            "month": "2023-02",
            "points": 150
        },
        {
            "month": "2023-01",
            "points": 120
        }
    ],
    "totalRewards": 270,
    "transactionDetails": [
        {
            "transactionId": 1,
            "date": "2023-01-05",
            "amount": 120.0,
            "rewardPoints": 90
        },
        {
            "transactionId": 2,
            "date": "2023-01-15",
            "amount": 80.0,
            "rewardPoints": 30
        },
        {
            "transactionId": 3,
            "date": "2023-02-10",
            "amount": 150.0,
            "rewardPoints": 150
        },
        {
            "transactionId": 4,
            "date": "2023-03-12",
            "amount": 40.0,
            "rewardPoints": 0
        }
    ]
}
```
2. Invalid Customer Id
<img width="1673" alt="image" src="https://github.com/user-attachments/assets/1a3739c3-e38a-454f-98b5-642ef1159043" />

3. Start date after end date

<img width="1369" alt="image" src="https://github.com/user-attachments/assets/f272c135-c798-421a-8e2c-faa2d968e82d" />

4. No Content for unknown user

<img width="1369" alt="image" src="https://github.com/user-attachments/assets/6334b4d4-a840-4635-af9f-0033591ecbe8" />

5. No Dates (default with 3 months)

<img width="1369" alt="image" src="https://github.com/user-attachments/assets/b27b9c2d-0bb9-4270-a9af-b0bc3054c7d8" />



