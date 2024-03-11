package com.vaultledger.controller;

import com.vaultledger.dto.DashboardSummary;
import com.vaultledger.service.FinanceService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final FinanceService financeService;

    public DashboardController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping
    public DashboardSummary getDashboard() {
        return financeService.getDashboard();
    }
}





