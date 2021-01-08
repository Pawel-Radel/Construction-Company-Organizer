package com.radello.constructioncompanyorganizer.services.costsServices;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.converter.CostToCostCommand;
import com.radello.constructioncompanyorganizer.domain.Cost;
import com.radello.constructioncompanyorganizer.repositories.CostRepository;
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
class CostsDependsOnTimeServiceImplTest {


    private final LocalDate TODAY_DATE = LocalDate.now();
    private final LocalDate ONE_MONTH_LATER = LocalDate.now().plusMonths(1);
    private final LocalDate TWO_MONTH_LATER = LocalDate.now().plusMonths(2);

    CostsDependsOnTimeServiceImpl costsDependsOnTimeService;

    @Mock
    CostRepository costRepository;

    @Mock
    CostToCostCommand costToCostCommand;

    List<Cost> costs;
    @Mock
    Cost cost1;
    @Mock
    Cost cost2;

    @BeforeEach
    void setUp() {
        costsDependsOnTimeService = new CostsDependsOnTimeServiceImpl(costRepository, costToCostCommand);

        costs = new ArrayList<>();
        costs.add(cost1);
        costs.add(cost2);

    }

    @Test
    void getCosts() {

        when(costToCostCommand.convert(any())).thenReturn(new CostCommand());
        when(costRepository.findAll(Sort.by("ID"))).thenReturn(costs);

        costsDependsOnTimeService.getCosts();

        verify(costRepository, times(1)).findAll(Sort.by("ID"));
        verify(costRepository, never()).save(any());
        verify(costToCostCommand, times(2)).convert(any());
    }

    @Test
    void getOutstandingCosts() {

        CostCommand command = new CostCommand();
        command.setScheduledtime(Date.valueOf(LocalDate.now().minusDays(1)));
        when(costToCostCommand.convert(any())).thenReturn(command);
        when(costRepository.findAll(Sort.by("ID"))).thenReturn(costs);

        List<CostCommand> listOfCosts = costsDependsOnTimeService.getCosts();
        List<CostCommand> costsAfterFilter = new ArrayList<>();

        listOfCosts
                .stream()
                .filter(costCommand -> costCommand.getScheduledtime().before(Date.valueOf(TODAY_DATE)))
                .forEach(costsAfterFilter::add);

        assertNotNull(costsAfterFilter);
        assertEquals(2, costsAfterFilter.size());

        verify(costRepository, times(1)).findAll(Sort.by("ID"));
        verify(costRepository, never()).save(any());
        verify(costToCostCommand, times(2)).convert(any());
    }

    @Test
    void getCostsNextMonth() {

        CostCommand command = new CostCommand();
        command.setScheduledtime(Date.valueOf(TODAY_DATE));
        when(costToCostCommand.convert(any())).thenReturn(command);
        when(costRepository.findAll(Sort.by("ID"))).thenReturn(costs);

        List <CostCommand> listOfCosts = costsDependsOnTimeService.getCosts();
        List <CostCommand> costsAfterFilter = new ArrayList<>();

        listOfCosts
                .stream()
                .filter(costCommand -> costCommand.getScheduledtime().after(Date.valueOf(TODAY_DATE.minusDays(1))))
                .filter(costCommand -> costCommand.getScheduledtime().before(Date.valueOf(ONE_MONTH_LATER.plusDays(1))))
                .forEach(costsAfterFilter::add);

        assertNotNull(costsAfterFilter);
        assertEquals(2, costsAfterFilter.size());

        verify(costRepository, times(1)).findAll(Sort.by("ID"));
        verify(costRepository, never()).save(any());
        verify(costToCostCommand,times(2)).convert(any());
    }

    @Test
    void getCostsAnotherMonth() {

        CostCommand command = new CostCommand();
        command.setScheduledtime(Date.valueOf(ONE_MONTH_LATER));
        when(costToCostCommand.convert(any())).thenReturn(command);
        when(costRepository.findAll(Sort.by("ID"))).thenReturn(costs);

        List <CostCommand> listOfCosts = costsDependsOnTimeService.getCosts();
        List <CostCommand> costsAfterFilter = new ArrayList<>();

        listOfCosts
                .stream()
                .filter(costCommand -> costCommand.getScheduledtime().after(Date.valueOf(ONE_MONTH_LATER.minusDays(1))))
                .filter(costCommand -> costCommand.getScheduledtime().before(Date.valueOf(TWO_MONTH_LATER.plusDays(1))))
                .forEach(costsAfterFilter::add);

        assertNotNull(costsAfterFilter);
        assertEquals(2, costsAfterFilter.size());

        verify(costRepository, times(1)).findAll(Sort.by("ID"));
        verify(costRepository, never()).save(any());
        verify(costToCostCommand,times(2)).convert(any());
    }

    @Test
    void furtherCosts() {

        CostCommand command = new CostCommand();
        command.setScheduledtime(Date.valueOf(TWO_MONTH_LATER.plusDays(2)));
        when(costToCostCommand.convert(any())).thenReturn(command);
        when(costRepository.findAll(Sort.by("ID"))).thenReturn(costs);

        List <CostCommand> listOfCosts = costsDependsOnTimeService.getCosts();
        List <CostCommand> costsAfterFilter = new ArrayList<>();

        listOfCosts
                .stream()
                .filter(costCommand -> costCommand.getScheduledtime().after(Date.valueOf(TWO_MONTH_LATER)))
                .forEach(costsAfterFilter::add);

        assertNotNull(costsAfterFilter);
        assertEquals(2, costsAfterFilter.size());

        verify(costRepository, times(1)).findAll(Sort.by("ID"));
        verify(costRepository, never()).save(any());
        verify(costToCostCommand,times(2)).convert(any());
    }
}