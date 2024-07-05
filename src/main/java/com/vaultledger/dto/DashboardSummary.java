package com.vaultledger.dto;

import java.util.List;
import java.util.Map;

public class DashboardSummary {

    private double totalIncome;
    private double totalExpenses;
    private double balance;
    private Map<String, Double> expensesByCategory;
    private List<CategoryBudgetStatus> budgetStatus;

    public DashboardSummary() {}

    public double getTotalIncome() { return totalIncome; }
    public void setTotalIncome(double totalIncome) { this.totalIncome = totalIncome; }

    public double getTotalExpenses() { return totalExpenses; }
    public void setTotalExpenses(double totalExpenses) { this.totalExpenses = totalExpenses; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

