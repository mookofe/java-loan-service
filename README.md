# Loan Microservice
Very small dummy Loan Microservice written using Java and Spring Framework. This service allows you to store and retrieve Loans using REST APIs.

- [Stack Summary](#stack-summary)
- [Public API Endpoints](#api-endpoints)

### Stack Summary:
- Java 11
- Maven 3.6
- Spring 5.2
- Spring Boot 2.2
- Spring Data
- MongoDB
- JUnit 5
- Mockito 3.1
- Docker 

## API Endpoints

### **List of endpoints:**

|URL|Method|Description|
---|---|---
**Loans:**||
[http://localhost:8000/api/v1/loans](#create-loan)|POST|Create loan
[http://localhost:8000/api/v1/loans/{id}](#get-loan-details)|GET|Get loan details

---
### Create Loan:
```
POST http://localhost:8000/api/v1/loans
Accept: application/json
```

**Payload:**

```
{
    "name": "John Doe",
    "ssn": "123-11-1133",
    "dateOfBirth": "1985-12-10",
    "loanAmount": 1000,
    "rate": 10,
    "loanType": "MORTGAGE",
    "term": 365
}
```

**Body Parameters:**

Name|Type|Required|Accepted values|Default|Description
---|---|---|---|---|---
name|String|Yes|||Name of the person applying for the loan
ssn|String|Yes|Format: 123-12-1234||Social Security Number of the person applying for the loan
dateOfBirth|String|Yes|Format: YYYY-MM-DD|| Date Of Birth of the person applying for the loan
loanAmount|Decimal|Yes|>= 1|| The amount that the person will be borrowing
rate|Decimal|Yes|> 0|| The current rate for the given loan
loanType|String|Yes|["STUDENT", "AUTO", "PERSONAL", "MORTGAGE"]||Loan type
term|Integer|Yes|>= 1|| The duration of the loan in days

**Response:**

```
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": "5ea0cb82aead3f24e4cdc5bd",
    "name": "John Doe",
    "ssn": "123-11-1133",
    "dateOfBirth": "1985-12-10",
    "loanAmount": 1000,
    "rate": 10,
    "loanType": "MORTGAGE",
    "term": 365,
    "apr": 10
}
```

---

### Get loan details
```
GET http://localhost:8000/api/v1/loans/{id}
Accept: application/json
```

**Query Parameters:**

Name|Type|Required|Description
---|---|---|---
id|String|Yes|Loan identifier

**Response:**

```
HTTP/1.1 200 OK
Content-Type: application/json

{
    "id": "5ea0cb82aead3f24e4cdc5bd",
    "name": "John Doe",
    "ssn": "123-11-1133",
    "dateOfBirth": "1985-12-10",
    "loanAmount": 1000,
    "rate": 10,
    "loanType": "MORTGAGE",
    "term": 365,
    "apr": "10"
}
```
