package com.radello.constructioncompanyorganizer.services;

import com.radello.constructioncompanyorganizer.domain.Cost;

import java.util.Set;

public interface CostsDependsOnTimeService {

    Set<Cost> getCosts();

    Set<Cost> getOutstandingCosts();
}
