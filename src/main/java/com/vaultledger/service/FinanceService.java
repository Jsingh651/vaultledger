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

