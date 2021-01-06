package com.radello.constructioncompanyorganizer.services;

import com.radello.constructioncompanyorganizer.commands.CostCommand;
import com.radello.constructioncompanyorganizer.converter.CostCommandToCost;
import com.radello.constructioncompanyorganizer.converter.CostToCostCommand;

import com.radello.constructioncompanyorganizer.domain.Cost;
import com.radello.constructioncompanyorganizer.repositories.CostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CostServiceImplTest {


    private static final int AMOUNT_VALUE = 1000;
    private final Long ID_VALUE = 1L;
    private final LocalDate TODAY_DATE = LocalDate.now();
    private final Date DAY_AFTER = Date.valueOf(LocalDate.now().plusDays(1));
    private final Date DAY_BEFORE = Date.valueOf(LocalDate.now().minusDays(1));
    private List<Cost> costs;
    private Cost cost;
    private Cost cost1;
    private Cost cost2;

    Optional<Cost> costFromOptional;


    CostServiceImpl costService;
    @Spy
    CostRepository costRepository;
    @Mock
    CostToCostCommand costToCostCommand;
    @Mock
    CostCommandToCost costCommandToCost;
    @Mock
    CostsDependsOnTimeServiceImpl costsDependsonTimeService;

    @BeforeEach
    void setUp() {

        costService = new CostServiceImpl(costRepository,
                costToCostCommand, costCommandToCost, costsDependsonTimeService);

        cost = new Cost();
        cost.setID(ID_VALUE);
        cost.setAmount(AMOUNT_VALUE);

        costFromOptional = Optional.of(cost);

        costs = new ArrayList<>();
        cost1 = new Cost();
        cost2 = new Cost();
        cost1.setScheduledtime(DAY_AFTER);
        cost2.setScheduledtime(DAY_BEFORE);
        costs.add(cost1);
        costs.add(cost2);
    }

    @Test
    void getCosts() {

        when(costRepository.findAll()).thenReturn(costs);

        Set <Cost> costs1 = costService.getCosts();

        assertNotNull(costs1);
        assertEquals(2, costService.getCosts().size());

        verify(costRepository, times(2)).findAll();
        verify(costRepository, never()).save(any());
    }

    @Test
    void findByID() {

        when(costRepository.findById(anyLong())).thenReturn(costFromOptional);

        Cost cost1 = costService.findByID(1L);

        assertNotNull(cost1, "Cost Not found");
        assertEquals(ID_VALUE, costFromOptional.get().getID());
        verify(costRepository, times(1)).findById(anyLong());
        verify(costRepository, never()).findAll();
    }

    @Test
    void findByIDNotFound() {

        Optional<Cost> costEmpty = Optional.empty();

        when(costRepository.findById(anyLong())).thenReturn(costEmpty);

        Cost costFromRepo = costService.findByID(anyLong());
        assertNull(costFromRepo);
        verify(costRepository, times(1)).findById(anyLong());
        verify(costRepository, never()).findAll();
    }

    @Test
    void findCommandByID() {

        when(costRepository.findById(anyLong())).thenReturn(costFromOptional);
        CostCommand costCommand = new CostCommand();
        costCommand.setID(ID_VALUE);

        when(costToCostCommand.convert(any())).thenReturn(costCommand);

        CostCommand costFromRepo = costService.findCommandByID(ID_VALUE);

        assertNotNull(costFromRepo, "Construction Order Command is Empty");
        verify(costRepository, times(1)).findById(anyLong());
        verify(costRepository, never()).findAll();

    }

    @Test
    void saveCostCommand() {

        CostCommand costCommand = new CostCommand();
        costCommand.setID(ID_VALUE);
        costCommand.setAmount(AMOUNT_VALUE);

        CostCommand costCommand1 = costService.saveCostCommand(costCommand);

        verify(costRepository, times(1)).save(any());
        verify(costRepository, never()).findAll();

    }

    @Test
    void deleteById() {
        costService.deleteById(ID_VALUE);

        verify(costRepository, times(1)).deleteById(anyLong());
        verify(costRepository,never()).save(any());

    }

}