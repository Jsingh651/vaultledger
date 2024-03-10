package com.vaultledger.controller;

import com.vaultledger.model.Budget;
import com.vaultledger.service.FinanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@CrossOrigin(origins = "*")
public class BudgetController {

    private final FinanceService financeService;

    public BudgetController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping
    public List<Budget> list(@RequestParam(defaultValue = "") String month) {
        if (month.isEmpty()) {
            month = java.time.LocalDate.now()
