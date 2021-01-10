package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.services.budgetServices.BudgetService;
import com.radello.constructioncompanyorganizer.services.costsServices.CostSumService;
import com.radello.constructioncompanyorganizer.services.costsServices.CostsDependsOnTimeService;
import com.radello.constructioncompanyorganizer.services.incomesServices.IncomeSumService;
import com.radello.constructioncompanyorganizer.services.incomesServices.IncomesDependsOnTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@ExtendWith(MockitoExtension.class)
class FinancialForecastControllerTest {

    FinancialForecastController financialForecastController;

    @Mock
    CostsDependsOnTimeService costsDependsOnTimeService;
    @Mock
    IncomesDependsOnTime incomesDependsOnTime;
    @Mock
    IncomeSumService incomeSumService;
    @Mock
    BudgetService budgetService;
    @Mock
    CostSumService costSumService;
    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        financialForecastController = new FinancialForecastController(costsDependsOnTimeService,
                incomesDependsOnTime, incomeSumService, costSumService, budgetService);
    }

    @Test
    void showFinancialForecast() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(financialForecastController).build();

        mockMvc.perform(get("/financialForecast"))
                .andExpect(status().isOk())
                .andExpect(view().name("FinancialForecast"));
    }

    @Test
    void Test() {

        List<CostCommand> comandset = new ArrayList<>();
        comandset.add(new CostCommand());
        comandset.add(new CostCommand());

        when(costsDependsOnTimeService.getCosts()).thenReturn(comandset);
        ArgumentCaptor<List<CostCommand>> argumentCaptor = ArgumentCaptor.forClass(List.class);

        String viewName = financialForecastController.showFinancialForecast(model);
        assertEquals("FinancialForecast", viewName);

        verify(model, times(1)).addAttribute(eq("costs"), argumentCaptor.capture());
        List<CostCommand> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }


}