package com.vaultledger.service;

import com.vaultledger.dto.CategoryBudgetStatus;
import com.vaultledger.dto.DashboardSummary;
import com.vaultledger.model.Budget;
import com.vaultledger.model.Transaction;
import com.vaultledger.model.TransactionType;
import com.vaultledger.repository.BudgetRepository;
import com.vaultledger.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FinanceService {

    private final TransactionRepository transactionRepo;
    private final BudgetRepository budgetRepo;

    public FinanceService(TransactionRepository transactionRepo, BudgetRepository budgetRepo) {
        this.transactionRepo = transactionRepo;
        this.budgetRepo = budgetRepo;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    public Optional<Transaction> getTransaction(Long id) {
        return transactionRepo.findById(id);
    }

    public void deleteTransaction(Long id) {
        transactionRepo.deleteById(id);
    }

    public List<Budget> getBudgets(String month) {
