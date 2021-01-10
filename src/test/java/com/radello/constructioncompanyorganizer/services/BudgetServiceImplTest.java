package com.radello.constructioncompanyorganizer.services;

import com.radello.constructioncompanyorganizer.domain.Budget;
import com.radello.constructioncompanyorganizer.repositories.BudgetRepository;
import com.radello.constructioncompanyorganizer.services.budgetServices.BudgetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BudgetServiceImplTest {

    private final int AMOUNT_VALUE = 1000;
    private final int AMOUNT_TO_CHANGE = 2000;
    private final Long ID_VALUE = 1L;

    BudgetServiceImpl budgetServiceImpl;

    Budget budget;
    Optional<Budget> budgetOptional;

    @Mock
    BudgetRepository budgetRepository;

    @BeforeEach
    void setUp() {
        budgetServiceImpl = new BudgetServiceImpl(budgetRepository);

        budget = new Budget();
        budget.setID(ID_VALUE);
        budget.setAmount(AMOUNT_VALUE);

        budgetOptional = Optional.of(budget);
    }

    @Test
    void find() {

        when(budgetRepository.findById(anyLong())).thenReturn(budgetOptional);

        Budget budgetFromRepo = budgetServiceImpl.find();

        assertNotNull(budgetFromRepo, "Budget is null");
        verify(budgetRepository, times(1)).findById(anyLong());
        verify(budgetRepository, never()).findAll();
        assertEquals(ID_VALUE, budgetFromRepo.getID());
        assertEquals(AMOUNT_VALUE, budgetFromRepo.getAmount());


    }

    @Test
    void increaseBudget() {

        when(budgetRepository.findById(anyLong())).thenReturn(budgetOptional);

        Budget budgetAfterIncrease = budgetServiceImpl.increaseBudget(AMOUNT_TO_CHANGE);

        assertEquals(3000, budgetAfterIncrease.getAmount());
        assertEquals(ID_VALUE, budgetAfterIncrease.getID());
        verify(budgetRepository, times(1)).findById(anyLong());
        verify(budgetRepository,never()).findAll();
    }

    @Test
    void decreaseBudget() {

        when(budgetRepository.findById(anyLong())).thenReturn(budgetOptional);

        Budget budgetAfterDecrease = budgetServiceImpl.decreaseBudget(AMOUNT_TO_CHANGE);

        assertEquals(-1000, budgetAfterDecrease.getAmount());
        assertEquals(ID_VALUE, budgetAfterDecrease.getID());
        verify(budgetRepository, times(1)).findById(anyLong());
        verify(budgetRepository,never()).findAll();
    }


}