package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.domain.Budget;
import com.radello.constructioncompanyorganizer.domain.Cost;
import com.radello.constructioncompanyorganizer.domain.Income;
import com.radello.constructioncompanyorganizer.services.budgetServices.BudgetService;
import com.radello.constructioncompanyorganizer.services.costsServices.CostService;
import com.radello.constructioncompanyorganizer.services.incomesServices.IncomeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@ExtendWith(MockitoExtension.class)
class BudgetControllerTest {

    BudgetController budgetController;
    MockMvc mockMvc;

    @Mock
    BudgetService budgetService;
    @Mock
    CostService costService;
    @Mock
    IncomeService incomeService;

    Budget budget;

    @BeforeEach
    void setUp() {
        budgetController = new BudgetController(budgetService, costService, incomeService);
        mockMvc = MockMvcBuilders.standaloneSetup(budgetController).build();
        budget = new Budget();
        budget.setAmount(20000);
        budget.setID(1L);

    }

    @Test
    void decreaseBudget() throws Exception {
        Cost cost = new Cost("Flat", LocalDate.now());
        cost.setAmount(1000);
        cost.setID(1L);

        when(costService.findByID(anyLong())).thenReturn(cost);
        when(budgetService.decreaseBudget(anyInt())).thenReturn(budget);

        budgetController.decreaseBudget("1");

        verify(budgetService, times(1)).decreaseBudget(anyInt());
        verify(costService, times(1)).findByID(anyLong());

        mockMvc.perform(get("/budget/1/decrease"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/cost/1/delete"));
    }

    @Test
    void increaseBudget() throws Exception {

        Income income = new Income();
        income.setAmount(1000);
        income.setID(1L);
        ;

        when(incomeService.findByID(anyLong())).thenReturn(income);
        when(budgetService.increaseBudget(anyInt())).thenReturn(budget);

        budgetController.increaseBudget("1");

        verify(budgetService, times(1)).increaseBudget(anyInt());
        verify(incomeService, times(1)).findByID(anyLong());

        mockMvc.perform(get("/budget/1/increase"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/income/1/delete"));
    }

    @Test
    void increaseBudget2() throws Exception {

        IncomeCommand incomeCommand = new IncomeCommand();
        incomeCommand.setAmount(1000);
        incomeCommand.setID(1L);
        Budget budget = new Budget();
        budget.setAmount(20000);
        budget.setID(1L);

        when(incomeService.findCommandByID(anyLong())).thenReturn(incomeCommand);
        when(budgetService.increaseBudget(anyInt())).thenReturn(budget);

        budgetController.increaseBudget2(anyLong(), "1");

        verify(budgetService, times(1)).increaseBudget(anyInt());
        verify(incomeService, times(1)).findCommandByID(anyLong());

        mockMvc.perform(post("/postpone/1/income"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/financialForecast"));
    }

    @Test
    void decreaseBudget2() throws Exception {

        CostCommand cost = new CostCommand();
        cost.setAmount(1000);
        cost.setID(1L);

        when(costService.findCommandByID(anyLong())).thenReturn(cost);
        when(budgetService.decreaseBudget(anyInt())).thenReturn(budget);

        budgetController.decreaseBudget2(anyLong(), "1");

        verify(budgetService, times(1)).decreaseBudget(anyInt());
        verify(costService, times(1)).findCommandByID(anyLong());

        mockMvc.perform(post("/postpone/1/cost"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/financialForecast"));
    }
}