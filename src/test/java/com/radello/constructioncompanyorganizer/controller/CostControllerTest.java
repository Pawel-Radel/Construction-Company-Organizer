package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.services.costsServices.CostService;
import com.radello.constructioncompanyorganizer.services.incomesServices.IncomeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class CostControllerTest {

    CostController costController;

    @Mock
    CostService costService;

    @Mock
    CostCommand command;

    @Mock
    BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        costController = new CostController(costService);
    }

    @Test
    void shownewCostTemplate() throws Exception{

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(costController).build();

        mockMvc.perform(get("/newCost/"))
                .andExpect(status().isOk())
                .andExpect(view().name("newCost"));
    }

    @Test
    void saveOrUpdate() {
    }
}