package com.vaultledger.controller;

import com.vaultledger.model.Transaction;
import com.vaultledger.service.FinanceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final FinanceService financeService;

    public TransactionController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping
    public List<Transaction> list() {
        return financeService.getAllTransactions();
    }

    @PostMapping
    public Transaction create(@Valid @RequestBody Transaction transaction) {
        return financeService.createTransaction(transaction);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> get(@PathVariable Long id) {
        return financeService.getTransaction(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
