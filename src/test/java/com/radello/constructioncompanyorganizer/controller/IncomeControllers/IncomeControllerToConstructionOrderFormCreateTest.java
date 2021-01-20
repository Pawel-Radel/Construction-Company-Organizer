package com.radello.constructioncompanyorganizer.controller.IncomeControllers;

import com.radello.constructioncompanyorganizer.commands.ConstructionOrderCommand;
import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.services.constructionOrderServices.ConstructionOrderService;
import com.radello.constructioncompanyorganizer.services.incomesServices.IncomeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(MockitoExtension.class)
class IncomeControllerToConstructionOrderFormCreateTest {

    IncomeControllerToConstructionOrderFormCreate incomeController;

    @Mock
    IncomeService incomeService;
    @Mock
    ConstructionOrderService constructionOrderService;
    @Mock
    Model model;

    MockMvc mockMvc;

    ConstructionOrderCommand command;

    @BeforeEach
    void setUp() {
        incomeController = new IncomeControllerToConstructionOrderFormCreate
                (incomeService, constructionOrderService);
        mockMvc = MockMvcBuilders.standaloneSetup(incomeController).build();

        command = new ConstructionOrderCommand();

    }

    @Test
    void addingIncomestoConstructionOrder() throws Exception {

        when(constructionOrderService.findCommandByID(anyLong())).thenReturn(command);

        mockMvc.perform(get("/newIncomeToConsOrder/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("IncomeTemplates/newIncomesToConsOrder"));

        String viewName = incomeController.addingIncomestoConstructionOrder(String.valueOf(1), model);
        assertEquals("IncomeTemplates/newIncomesToConsOrder", viewName);

        verify(constructionOrderService, times(2)).findCommandByID(anyLong());
        verify(incomeService, times(2)).sortSet(anySet());
        verify(incomeService, times(2)).sumValues(anyList());
    }

    @Test
    void addingIncomeToConstructionOrder() throws Exception {

        when(constructionOrderService.findCommandByID(anyLong())).thenReturn(command);
        when(incomeService.saveIncomeCommand(any())).thenReturn(new IncomeCommand());

        String viewName = incomeController.addingIncomeToConstructionOrder(new IncomeCommand(), String.valueOf(1), "05/05/2021");
        assertEquals("redirect:/newIncomeToConsOrder/1", viewName);

        verify(constructionOrderService, times(1)).findCommandByID(anyLong());
        verify(incomeService,times(1)).saveIncomeCommand(any());
        verify(constructionOrderService, times(1)).saveConstructionOrderCommand(any());
    }

    @Test
    void deleteIncomefromConstructionOrderById() {
    }

    @Test
    void editIndicativeCostById() {
    }

    @Test
    void editIndicativeCost() {
    }

}