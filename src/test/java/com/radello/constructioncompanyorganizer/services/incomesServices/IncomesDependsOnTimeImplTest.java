package com.radello.constructioncompanyorganizer.services.incomesServices;

import com.radello.constructioncompanyorganizer.commands.IncomeCommand;
import com.radello.constructioncompanyorganizer.converter.IncomeToIncomeCommand;
import com.radello.constructioncompanyorganizer.domain.Income;
import com.radello.constructioncompanyorganizer.repositories.IncomeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IncomesDependsOnTimeImplTest {

    private final LocalDate TODAY_DATE = LocalDate.now();
    private final LocalDate ONE_MONTH_LATER = LocalDate.now().plusMonths(1);
    private final LocalDate TWO_MONTH_LATER = LocalDate.now().plusMonths(2);

    IncomesDependsOnTimeImpl incomesDependsOnTimeImpl;

    @Mock
    IncomeRepository incomeRepository;

    @Mock
    IncomeToIncomeCommand incomeToIncomeCommand;


    List<Income> incomes;
    @Mock
    Income income1;
    @Mock
    Income income2;

    @BeforeEach
    void setUp() {
        incomesDependsOnTimeImpl = new IncomesDependsOnTimeImpl(incomeRepository,incomeToIncomeCommand);
        //income1 = new Income();
        //income2 = new Income();
        incomes = new ArrayList<>();
        income1.setScheduledTimeToGet(Date.valueOf(LocalDate.now().minusDays(1)));
        income2.setScheduledTimeToGet(Date.valueOf(LocalDate.now()));
        incomes.add(income1);
        incomes.add(income2);
    }

    @Test
    void getIncomes() {

        when(incomeToIncomeCommand.convert(any())).thenReturn(new IncomeCommand());
        when(incomeRepository.findAll(Sort.by("ID"))).thenReturn(incomes);

        List <IncomeCommand> income = incomesDependsOnTimeImpl.getIncomes();

        assertNotNull(income);

        assertEquals(2, income.size());

        verify(incomeRepository, times(1)).findAll(Sort.by("ID"));
        verify(incomeRepository, never()).save(any());
        verify(incomeToIncomeCommand,times(2)).convert(any());
    }

    @Test
    void getOutstandingIncomes() {

        IncomeCommand command = new IncomeCommand();
        command.setScheduledTimeToGet(Date.valueOf(LocalDate.now().minusDays(1)));

        when(incomeToIncomeCommand.convert(any())).thenReturn(command);
        when(incomeRepository.findAll(Sort.by("ID"))).thenReturn(incomes);

        List <IncomeCommand> income = incomesDependsOnTimeImpl.getIncomes();
        List <IncomeCommand> incomeAfterFilter = new ArrayList<>();

        income
                .stream()
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().before(Date.valueOf(TODAY_DATE)))
                .forEach(incomeAfterFilter::add);

        assertNotNull(incomeAfterFilter);
        assertEquals(2, incomeAfterFilter.size());
        verify(incomeRepository, times(1)).findAll(Sort.by("ID"));
        verify(incomeRepository, never()).save(any());
        verify(incomeToIncomeCommand,times(2)).convert(any());
    }

    @Test
    void getIncomesNextMonth() {

        IncomeCommand command = new IncomeCommand();
        command.setScheduledTimeToGet(Date.valueOf(LocalDate.now().plusDays(1)));

        when(incomeToIncomeCommand.convert(any())).thenReturn(command);
        when(incomeRepository.findAll(Sort.by("ID"))).thenReturn(incomes);

        List <IncomeCommand> income = incomesDependsOnTimeImpl.getIncomes();
        List <IncomeCommand> incomeAfterFilter = new ArrayList<>();

        income
                .stream()
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().after(Date.valueOf(TODAY_DATE.minusDays(1))))
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().before(Date.valueOf(ONE_MONTH_LATER.plusDays(1))))
                .forEach(incomeAfterFilter::add);

        assertNotNull(incomeAfterFilter);
        assertEquals(2, incomeAfterFilter.size());
        verify(incomeRepository, times(1)).findAll(Sort.by("ID"));
        verify(incomeRepository, never()).save(any());
        verify(incomeToIncomeCommand,times(2)).convert(any());
    }

    @Test
    void getIncomesAnotherMonth() {

        IncomeCommand command = new IncomeCommand();
        command.setScheduledTimeToGet(Date.valueOf(ONE_MONTH_LATER));

        when(incomeToIncomeCommand.convert(any())).thenReturn(command);
        when(incomeRepository.findAll(Sort.by("ID"))).thenReturn(incomes);

        List <IncomeCommand> income = incomesDependsOnTimeImpl.getIncomes();
        List <IncomeCommand> incomeAfterFilter = new ArrayList<>();

        income
                .stream()
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().after(Date.valueOf(ONE_MONTH_LATER.minusDays(1))))
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().before(Date.valueOf(TWO_MONTH_LATER.plusDays(1))))
                .forEach(incomeAfterFilter::add);

        assertNotNull(incomeAfterFilter);
        assertEquals(2, incomeAfterFilter.size());
        verify(incomeRepository, times(1)).findAll(Sort.by("ID"));
        verify(incomeRepository, never()).save(any());
        verify(incomeToIncomeCommand,times(2)).convert(any());
    }

    @Test
    void getFurtherIncomes() {

        IncomeCommand command = new IncomeCommand();
        command.setScheduledTimeToGet(Date.valueOf(TWO_MONTH_LATER.plusDays(1)));

        when(incomeToIncomeCommand.convert(any())).thenReturn(command);
        when(incomeRepository.findAll(Sort.by("ID"))).thenReturn(incomes);

        List <IncomeCommand> income = incomesDependsOnTimeImpl.getIncomes();
        List <IncomeCommand> incomeAfterFilter = new ArrayList<>();

        income
                .stream()
                .filter(incomeCommand -> incomeCommand.getScheduledTimeToGet().after(Date.valueOf(TWO_MONTH_LATER)))
                .forEach(incomeAfterFilter::add);

        assertNotNull(incomeAfterFilter);
        assertEquals(2, incomeAfterFilter.size());
        verify(incomeRepository, times(1)).findAll(Sort.by("ID"));
        verify(incomeRepository, never()).save(any());
        verify(incomeToIncomeCommand,times(2)).convert(any());

    }
}