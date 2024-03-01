package com.vaultledger.config;

import com.vaultledger.model.Budget;
import com.vaultledger.model.Transaction;
import com.vaultledger.model.TransactionType;
import com.vaultledger.repository.BudgetRepository;
import com.vaultledger.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class DataSeeder implements CommandLineRunner {

    private final TransactionRepository transactionRepo;
    private final BudgetRepository budgetRepo;

    public DataSeeder(TransactionRepository transactionRepo, BudgetRepository budgetRepo) {
        this.transactionRepo = transactionRepo;
        this.budgetRepo = budgetRepo;
    }

    @Override
    public void run(String... args) {
        if (transactionRepo.count() > 0) return;

        String month = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));

        transactionRepo.save(makeTransaction("Salary", 5200.0, TransactionType.INCOME, "Income", LocalDate.now().minusDays(5)));
        transactionRepo.save(makeTransaction("Freelance project", 850.0, TransactionType.INCOME, "Income", LocalDate.now().minusDays(12)));
        transactionRepo.save(makeTransaction("Rent", 1400.0, TransactionType.EXPENSE, "Housing", LocalDate.now().minusDays(3)));
        transactionRepo.save(makeTransaction("Grocery run", 127.50, TransactionType.EXPENSE, "Food", LocalDate.now().minusDays(1)));
        transactionRepo.save(makeTransaction("Netflix", 15.99, TransactionType.EXPENSE, "Entertainment", LocalDate.now().minusDays(7)));
        transactionRepo.save(makeTransaction("Gas", 45.00, TransactionType.EXPENSE, "Transport", LocalDate.now().minusDays(2)));
        transactionRepo.save(makeTransaction("Coffee shop", 6.50, TransactionType.EXPENSE, "Food", LocalDate.now()));

        budgetRepo.save(makeBudget("Food", 400.0, month));
        budgetRepo.save(makeBudget("Entertainment", 100.0, month));
        budgetRepo.save(makeBudget("Transport", 200.0, month));
    }

    private Transaction makeTransaction(String desc, double amount, TransactionType type, String cat, LocalDate date) {
        Transaction t = new Transaction();
        t.setDescription(desc);
        t.setAmount(amount);
        t.setType(type);
        t.setCategory(cat);
        t.setDate(date);
        return t;
    }

    private Budget makeBudget(String category, double limit, String month) {
        Budget b = new Budget();
        b.setCategory(category);
        b.setLimitAmount(limit);
        b.setMonth(month);
        return b;
    }
}
