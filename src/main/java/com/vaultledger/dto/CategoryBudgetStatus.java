package com.vaultledger.dto;

public class CategoryBudgetStatus {

    private String category;
    private double spent;
    private double limit;
    private double percentUsed;

    public CategoryBudgetStatus() {}

    public CategoryBudgetStatus(String category, double spent, double limit) {
        this.category = category;
        this.spent = spent;
        this.limit = limit;
        this.percentUsed = limit > 0 ? (spent / limit) * 100 : 0;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getSpent() { return spent; }
    public void setSpent(double spent) { this.spent = spent; }

    public double getLimit() { return limit; }
    public void setLimit(double limit) { this.limit = limit; }

    public double getPercentUsed() { return percentUsed; }
    public void setPercentUsed(double percentUsed) { this.percentUsed = percentUsed; }
}
