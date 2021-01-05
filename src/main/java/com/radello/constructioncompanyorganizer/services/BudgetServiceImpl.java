package com.radello.constructioncompanyorganizer.services;

import com.radello.constructioncompanyorganizer.domain.Budget;
import com.radello.constructioncompanyorganizer.repositories.BudgetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {

    BudgetRepository budgetRepository;

    public BudgetServiceImpl(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public Budget find() {
        Optional<Budget> budgetOptional = budgetRepository.findById(1L);

        return budgetOptional.orElse(null);
    }

    @Override
    @Transactional
    public Budget increaseBudget(int amount) {
        Budget budget = find();

        budget.setAmount(budget.getAmount() + amount);
        budgetRepository.save(budget);

        return budget;
    }

    @Override
    @Transactional
    public Budget decreaseBudget(int amount) {
        Budget budget = find();

        budget.setAmount(budget.getAmount() - amount);
        budgetRepository.save(budget);

        return budget;
    }
}
