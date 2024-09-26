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
        return budgetRepo.findByMonth(month);
    }

    public Budget createBudget(Budget budget) {
        return budgetRepo.save(budget);
    }

    public DashboardSummary getDashboard() {
        Double income = transactionRepo.sumByType(TransactionType.INCOME);
        Double expenses = transactionRepo.sumByType(TransactionType.EXPENSE);
        double totalIncome = income != null ? income : 0;
        double totalExpenses = expenses != null ? expenses : 0;

        Map<String, Double> byCategory = transactionRepo.findAll().stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE)
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        String currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM"));
        List<CategoryBudgetStatus> budgetStatus = budgetRepo.findByMonth(currentMonth).stream()
                .map(b -> {
                    Double spent = transactionRepo.sumExpensesByCategory(b.getCategory());
                    return new CategoryBudgetStatus(
                            b.getCategory(),
                            spent != null ? spent : 0,
                            b.getLimitAmount()
                    );
                })
                .collect(Collectors.toList());

        DashboardSummary summary = new DashboardSummary();
        summary.setTotalIncome(totalIncome);
        summary.setTotalExpenses(totalExpenses);
        summary.setBalance(totalIncome - totalExpenses);
        summary.setExpensesByCategory(byCategory);
        summary.setBudgetStatus(budgetStatus);
        return summary;
    }
}



