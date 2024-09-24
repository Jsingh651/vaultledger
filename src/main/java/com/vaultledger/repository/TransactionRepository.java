package com.vaultledger.repository;

import com.vaultledger.model.Transaction;
import com.vaultledger.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByDateBetweenOrderByDateDesc(LocalDate start, LocalDate end);

    List<Transaction> findByCategory(String category);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.type = :type")
    Double sumByType(TransactionType type);

    @Query("SELECT COALESCE(SUM(t.amount), 0) FROM Transaction t WHERE t.type = 'EXPENSE' AND t.category = :category")
    Double sumExpensesByCategory(String category);
}





