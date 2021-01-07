package com.radello.constructioncompanyorganizer.services.costsServices;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.converter.CostToCostCommand;
import com.radello.constructioncompanyorganizer.domain.Cost;
import com.radello.constructioncompanyorganizer.repositories.CostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CostsDependsOnTimeServiceImplTest {

    CostsDependsOnTimeServiceImpl costsDependsOnTimeService;

    @Mock
    CostRepository costRepository;

    @Mock
    CostToCostCommand costToCostCommand;

    List<Cost> costs;
    Cost cost1;
    Cost cost2;

    @BeforeEach
    void setUp() {
        costsDependsOnTimeService = new CostsDependsOnTimeServiceImpl(costRepository,costToCostCommand);

        costs = new ArrayList<>();
        costs.add(cost1);
        costs.add(cost2);
    }

    @Test
    void getCosts() {

        when(costToCostCommand.convert(any())).thenReturn(new CostCommand());
        when(costRepository.findAll()).thenReturn(costs);

        costsDependsOnTimeService.getCosts();

        verify(costRepository, times(1)).findAll();
        verify(costRepository, never()).save(any());
        verify(costToCostCommand,times(2)).convert(any());
    }

    @Test
    void getOutstandingCosts() {

        when(costToCostCommand.convert(any())).thenReturn(new CostCommand());
        when(costRepository.findAll()).thenReturn(costs);

        costsDependsOnTimeService.getCosts();

        verify(costRepository, times(1)).findAll();
        verify(costRepository, never()).save(any());
        verify(costToCostCommand,times(2)).convert(any());
    }

    @Test
    void getCostsNextMonth() {

        when(costToCostCommand.convert(any())).thenReturn(new CostCommand());
        when(costRepository.findAll()).thenReturn(costs);

        costsDependsOnTimeService.getCosts();

        verify(costRepository, times(1)).findAll();
        verify(costRepository, never()).save(any());
        verify(costToCostCommand,times(2)).convert(any());
    }

    @Test
    void getCostsAnotherMonth() {

        when(costToCostCommand.convert(any())).thenReturn(new CostCommand());
        when(costRepository.findAll()).thenReturn(costs);

        costsDependsOnTimeService.getCosts();

        verify(costRepository, times(1)).findAll();
        verify(costRepository, never()).save(any());
        verify(costToCostCommand,times(2)).convert(any());
    }

    @Test
    void furtherCosts() {

        when(costToCostCommand.convert(any())).thenReturn(new CostCommand());
        when(costRepository.findAll()).thenReturn(costs);

        costsDependsOnTimeService.getCosts();

        verify(costRepository, times(1)).findAll();
        verify(costRepository, never()).save(any());
        verify(costToCostCommand,times(2)).convert(any());
    }
}