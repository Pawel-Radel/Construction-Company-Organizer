package com.radello.constructioncompanyorganizer.services;

import com.radello.constructioncompanyorganizer.domain.Budget;

public interface BudgetService {

    Budget find();

    Budget increaseBudget (int amount);

    Budget decreaseBudget (int amount);
}
