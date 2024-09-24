package com.vaultledger.repository;

import com.vaultledger.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findByMonth(String month);

    Optional<Budget> findByCategoryAndMonth(String category, String month);
}




