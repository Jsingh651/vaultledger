package com.vaultledger.dto;

import java.util.List;
import java.util.Map;

public class DashboardSummary {

    private double totalIncome;
    private double totalExpenses;
    private double balance;
    private Map<String, Double> expensesByCategory;
    private List<CategoryBudgetStatus> budgetStatus;
