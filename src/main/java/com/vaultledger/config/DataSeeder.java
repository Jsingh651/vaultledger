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

