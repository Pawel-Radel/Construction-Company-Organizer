package com.radello.constructioncompanyorganizer.repositories;

import com.radello.constructioncompanyorganizer.domain.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository <Budget, Long> {
}
