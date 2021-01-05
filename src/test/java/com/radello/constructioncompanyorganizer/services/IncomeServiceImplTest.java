package com.radello.constructioncompanyorganizer.services;

import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.converter.IncomeCommandToIncome;
import com.radello.constructioncompanyorganizer.converter.IncomeToIncomeCommand;
import com.radello.constructioncompanyorganizer.domain.Income;
import com.radello.constructioncompanyorganizer.repositories.IncomeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IncomeServiceImplTest {

    private static final int AMOUNT_VALUE = 1000;
    private final Long ID_VALUE = 1L;

    Income income;

    IncomeServiceImpl incomeService;

    Optional<Income> incomeOptional;

    @Mock
    IncomeRepository incomeRepository;
    @Mock
    IncomeToIncomeCommand incomeToIncomeCommand;
    @Mock
    IncomeCommandToIncome incomeCommandToIncome;


    @BeforeEach
    void setUp() {
        incomeService = new IncomeServiceImpl
                (incomeRepository, incomeToIncomeCommand, incomeCommandToIncome);

        income = new Income();
        income.setID(ID_VALUE);
        income.setAmount(AMOUNT_VALUE);

        incomeOptional = Optional.of(income);
    }

    @Test
    void getIncomes() {

        incomeService.getIncomes();

        verify(incomeRepository, times(1)).findAll();
        verify(incomeRepository, never()).save(any());
    }

    @Test
    void findByID() {

        when(incomeRepository.findById(anyLong())).thenReturn(incomeOptional);

        Income income = incomeService.findByID(1L);

        assertNotNull(income, "Income Not found");
        assertEquals(ID_VALUE, incomeOptional.get().getID());
        verify(incomeRepository, times(1)).findById(anyLong());
        verify(incomeRepository, never()).findAll();
    }

    @Test
    void findCommandByID() {

        when(incomeRepository.findById(anyLong())).thenReturn(incomeOptional);
        IncomeCommand incomeCommand = new IncomeCommand();
        incomeCommand.setID(ID_VALUE);

        when(incomeToIncomeCommand.convert(any())).thenReturn(incomeCommand);

        IncomeCommand incomeFromRepo = incomeService.findCommandByID(ID_VALUE);

        assertNotNull(incomeFromRepo, "Income Command is Empty");
        verify(incomeRepository, times(1)).findById(anyLong());
        verify(incomeRepository, never()).findAll();
    }

    @Test
    void saveIncomeCommand() {

        IncomeCommand incomeCommand = new IncomeCommand();
        incomeCommand.setID(ID_VALUE);
        incomeCommand.setAmount(AMOUNT_VALUE);

        incomeService.saveIncomeCommand(incomeCommand);

        verify(incomeRepository, times(1)).save(any());
        verify(incomeRepository, never()).findAll();
    }

    @Test
    void deleteById() {

        incomeService.deleteById(ID_VALUE);

        verify(incomeRepository, times(1)).deleteById(anyLong());
        verify(incomeRepository,never()).save(any());

    }
}