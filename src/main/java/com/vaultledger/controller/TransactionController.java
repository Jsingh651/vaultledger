package com.vaultledger.controller;

import com.vaultledger.model.Transaction;
import com.vaultledger.service.FinanceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
