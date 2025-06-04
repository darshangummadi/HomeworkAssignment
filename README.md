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

Let me know if you want to include a link to a Postman collection or want a `.http` file added for local testing.
