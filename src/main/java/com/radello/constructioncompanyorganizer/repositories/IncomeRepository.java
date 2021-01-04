package com.radello.constructioncompanyorganizer.repositories;

import com.radello.constructioncompanyorganizer.domain.Income;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeRepository extends JpaRepository<Income, Long> {
}
