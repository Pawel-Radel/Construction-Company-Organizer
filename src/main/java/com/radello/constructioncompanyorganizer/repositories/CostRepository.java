package com.radello.constructioncompanyorganizer.repositories;

import com.radello.constructioncompanyorganizer.domain.Cost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CostRepository extends JpaRepository<Cost, Long> {
}
