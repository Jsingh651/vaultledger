# VaultLedger

Personal finance tracker with a Spring Boot REST API and web dashboard. Track income, expenses, and budgets with category breakdowns.

## Features

- Transaction management (income/expense)
- Budget tracking with progress bars
- Dashboard with spending analytics
- H2 embedded database with seed data
- REST API for integrations

## Quick Start

```bash
mvn spring-boot:run
```

Open http://localhost:8080

## API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/dashboard` | Summary stats |
| GET | `/api/transactions` | List transactions |
| POST | `/api/transactions` | Create transaction |
| DELETE | `/api/transactions/{id}` | Delete transaction |
| GET | `/api/budgets` | List budgets |

## Requirements

- Java 17+
- Maven 3.8+


