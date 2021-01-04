package com.radello.constructioncompanyorganizer.repositories;

import com.radello.constructioncompanyorganizer.domain.ConstructionOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConstructionOrderRepository extends JpaRepository <ConstructionOrder, Long> {
}
