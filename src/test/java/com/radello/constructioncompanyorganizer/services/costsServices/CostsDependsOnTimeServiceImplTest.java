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
    List<CostCommand> listOfCosts;
    List<CostCommand> costsAfterFilter;
    @Mock
    CostRepository costRepository;

    @Mock
    CostToCostCommand costToCostCommand;
    CostCommand command;
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
        command = new CostCommand();

        costsAfterFilter = new ArrayList<>();

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

        command.setScheduledtime(LocalDate.now().minusDays(1));
        when(costToCostCommand.convert(any())).thenReturn(command);
        when(costRepository.findAll(Sort.by("ID"))).thenReturn(costs);
        listOfCosts = costsDependsOnTimeService.getCosts();

        listOfCosts
                .stream()
                .filter(costCommand -> costCommand.getScheduledtime().isBefore(TODAY_DATE))
                .forEach(costsAfterFilter::add);

        assertNotNull(costsAfterFilter);
        assertEquals(2, costsAfterFilter.size());

        verify(costRepository, times(1)).findAll(Sort.by("ID"));
        verify(costRepository, never()).save(any());
        verify(costToCostCommand, times(2)).convert(any());
    }

    @Test
    void getCostsNextMonth() {

        command.setScheduledtime(TODAY_DATE);
        when(costToCostCommand.convert(any())).thenReturn(command);
        when(costRepository.findAll(Sort.by("ID"))).thenReturn(costs);
        listOfCosts = costsDependsOnTimeService.getCosts();

        listOfCosts
                .stream()
                .filter(costCommand -> costCommand.getScheduledtime().isAfter(TODAY_DATE.minusDays(1)))
                .filter(costCommand -> costCommand.getScheduledtime().isBefore(ONE_MONTH_LATER.plusDays(1)))
                .forEach(costsAfterFilter::add);

        assertNotNull(costsAfterFilter);
        assertEquals(2, costsAfterFilter.size());

        verify(costRepository, times(1)).findAll(Sort.by("ID"));
        verify(costRepository, never()).save(any());
        verify(costToCostCommand, times(2)).convert(any());
    }

    @Test
    void getCostsAnotherMonth() {

        command = new CostCommand();
        command.setScheduledtime(ONE_MONTH_LATER);
        when(costToCostCommand.convert(any())).thenReturn(command);
        when(costRepository.findAll(Sort.by("ID"))).thenReturn(costs);
        listOfCosts = costsDependsOnTimeService.getCosts();

        listOfCosts
                .stream()
                .filter(costCommand -> costCommand.getScheduledtime().isAfter(ONE_MONTH_LATER.minusDays(1)))
                .filter(costCommand -> costCommand.getScheduledtime().isBefore(TWO_MONTH_LATER.plusDays(1)))
                .forEach(costsAfterFilter::add);

        assertNotNull(costsAfterFilter);
        assertEquals(2, costsAfterFilter.size());

        verify(costRepository, times(1)).findAll(Sort.by("ID"));
        verify(costRepository, never()).save(any());
        verify(costToCostCommand, times(2)).convert(any());
    }

    @Test
    void furtherCosts() {

        command.setScheduledtime(TWO_MONTH_LATER.plusDays(2));
        when(costToCostCommand.convert(any())).thenReturn(command);
        when(costRepository.findAll(Sort.by("ID"))).thenReturn(costs);
        listOfCosts = costsDependsOnTimeService.getCosts();

        listOfCosts
                .stream()
                .filter(costCommand -> costCommand.getScheduledtime().isAfter(TWO_MONTH_LATER))
                .forEach(costsAfterFilter::add);

        assertNotNull(costsAfterFilter);
        assertEquals(2, costsAfterFilter.size());

        verify(costRepository, times(1)).findAll(Sort.by("ID"));
        verify(costRepository, never()).save(any());
        verify(costToCostCommand, times(2)).convert(any());
    }
}