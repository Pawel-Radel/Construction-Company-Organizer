package com.radello.constructioncompanyorganizer.controller;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.services.costsServices.CostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
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

    MockMvc mockMvc;

    @Mock
    BindingResult bindingResult;

    @Mock
    Model model;

    @BeforeEach
    void setUp() {
        costController = new CostController(costService);
        mockMvc = MockMvcBuilders.standaloneSetup(costController).build();
    }

    @Test
    void showNewCostTemplate() throws Exception {


        mockMvc.perform(get("/newCost/"))
                .andExpect(status().isOk())
                .andExpect(view().name("newCost"));
    }

    @Test
    void saveOrUpdate() throws Exception{

        command.setAmount(1000);
        command.setForWhat("For Flat");
        command.setID(1L);
        when(costService.saveCostCommand(any())).thenReturn(command);

        String viewName = costController.saveOrUpdate(command, bindingResult, new String("01/01/2020"), model);

        assertEquals("redirect:/financialForecast", viewName);
        verify(costService, times(1)).saveCostCommand(any());
        verify(costService, never()).findCommandByID(any());
    }

    @Test
    void deleteById() throws Exception {

        mockMvc.perform(get("/cost/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/financialForecast"));

        verify(costService, times(1)).deleteById(anyLong());
    }
}