package com.vaultledger.controller;

import com.vaultledger.model.Budget;
import com.vaultledger.service.FinanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@CrossOrigin(origins = "*")
