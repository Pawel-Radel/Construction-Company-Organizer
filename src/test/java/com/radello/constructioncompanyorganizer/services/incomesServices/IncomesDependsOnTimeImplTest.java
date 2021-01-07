package com.radello.constructioncompanyorganizer.services.incomesServices;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.converter.CostToCostCommand;
import com.radello.constructioncompanyorganizer.converter.IncomeToIncomeCommand;
import com.radello.constructioncompanyorganizer.domain.Cost;
import com.radello.constructioncompanyorganizer.domain.Income;
import com.radello.constructioncompanyorganizer.repositories.CostRepository;
import com.radello.constructioncompanyorganizer.repositories.IncomeRepository;
import com.radello.constructioncompanyorganizer.services.costsServices.CostsDependsOnTimeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IncomesDependsOnTimeImplTest {

    IncomesDependsOnTimeImpl incomesDependsOnTimeImpl;

    @Mock
    IncomeRepository incomeRepository;

    @Mock
    IncomeToIncomeCommand incomeToIncomeCommand;

    List<Income> incomes;
    Income income1;
    Income income2;


    @BeforeEach
    void setUp() {
        incomesDependsOnTimeImpl = new IncomesDependsOnTimeImpl(incomeRepository,incomeToIncomeCommand);

        incomes = new ArrayList<>();
        incomes.add(income1);
        incomes.add(income2);
    }

    @Test
    void getIncomes() {

        when(incomeToIncomeCommand.convert(any())).thenReturn(new IncomeCommand());
        when(incomeRepository.findAll()).thenReturn(incomes);

        incomesDependsOnTimeImpl.getIncomes();

        verify(incomeRepository, times(1)).findAll();
        verify(incomeRepository, never()).save(any());
        verify(incomeToIncomeCommand,times(2)).convert(any());
    }

    @Test
    void getOutstandingIncomes() {

        when(incomeToIncomeCommand.convert(any())).thenReturn(new IncomeCommand());
        when(incomeRepository.findAll()).thenReturn(incomes);

        incomesDependsOnTimeImpl.getIncomes();

        verify(incomeRepository, times(1)).findAll();
        verify(incomeRepository, never()).save(any());
        verify(incomeToIncomeCommand,times(2)).convert(any());
    }

    @Test
    void getIncomesNextMonth() {

        when(incomeToIncomeCommand.convert(any())).thenReturn(new IncomeCommand());
        when(incomeRepository.findAll()).thenReturn(incomes);

        incomesDependsOnTimeImpl.getIncomes();

        verify(incomeRepository, times(1)).findAll();
        verify(incomeRepository, never()).save(any());
        verify(incomeToIncomeCommand,times(2)).convert(any());
    }

    @Test
    void getIncomesAnotherMonth() {

        when(incomeToIncomeCommand.convert(any())).thenReturn(new IncomeCommand());
        when(incomeRepository.findAll()).thenReturn(incomes);

        incomesDependsOnTimeImpl.getIncomes();

        verify(incomeRepository, times(1)).findAll();
        verify(incomeRepository, never()).save(any());
        verify(incomeToIncomeCommand,times(2)).convert(any());
    }

    @Test
    void getFurtherIncomes() {
        when(incomeToIncomeCommand.convert(any())).thenReturn(new IncomeCommand());
        when(incomeRepository.findAll()).thenReturn(incomes);

        incomesDependsOnTimeImpl.getIncomes();

        verify(incomeRepository, times(1)).findAll();
        verify(incomeRepository, never()).save(any());
        verify(incomeToIncomeCommand,times(2)).convert(any());
    }
}