package com.radello.constructioncompanyorganizer.services;

import com.radello.constructioncompanyorganizer.commands.IndicativeCostCommand;
import com.radello.constructioncompanyorganizer.converter.IndicativeCostCommandToIndicativeCost;
import com.radello.constructioncompanyorganizer.converter.IndicativeCostToIndicativeCostCommand;
import com.radello.constructioncompanyorganizer.domain.IndicativeCost;
import com.radello.constructioncompanyorganizer.repositories.IndicativeCostRepository;
import com.radello.constructioncompanyorganizer.services.indicativeCostServices.IndicativeCostServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class IndicativeCostServiceImplTest {

    private final Long ID_VALUE = 1L;

    IndicativeCost indicativeCost;

    IndicativeCostServiceImpl indicativeCostService;

    Optional<IndicativeCost> optionalIndicativeCost;

    @Mock
    IndicativeCostRepository indicativeCostRepository;
    @Mock
    IndicativeCostToIndicativeCostCommand indicativeCostToIndicativeCostCommand;
    @Mock
    IndicativeCostCommandToIndicativeCost indicativeCostCommandToIndicativeCost;

    @BeforeEach
    void setUp() {

        indicativeCostService = new IndicativeCostServiceImpl
                (indicativeCostRepository,
                        indicativeCostToIndicativeCostCommand,
                        indicativeCostCommandToIndicativeCost);

        indicativeCost = new IndicativeCost();
        indicativeCost.setID(ID_VALUE);

        optionalIndicativeCost = Optional.of(indicativeCost);
    }

    @Test
    void getIndicativeCosts() {


        indicativeCostService.getIndicativeCosts();

        verify(indicativeCostRepository, times(1)).findAll();
        verify(indicativeCostRepository, never()).save(any());
    }

    @Test
    void findByID() {

        when(indicativeCostRepository.findById(anyLong())).thenReturn(optionalIndicativeCost);

        IndicativeCost indicativeCost = indicativeCostService.findByID(1L);

        assertNotNull(indicativeCost, "indicativeCost Not found");
        assertEquals(ID_VALUE, optionalIndicativeCost.get().getID());
        verify(indicativeCostRepository, times(1)).findById(anyLong());
        verify(indicativeCostRepository, never()).findAll();
    }

    @Test
    void findCommandByID() {

        when(indicativeCostRepository.findById(anyLong())).thenReturn(optionalIndicativeCost);
        IndicativeCostCommand indicativeCostCommand = new IndicativeCostCommand();
        indicativeCostCommand.setID(ID_VALUE);

        when(indicativeCostToIndicativeCostCommand.convert(any())).thenReturn(indicativeCostCommand);

        IndicativeCostCommand indCostFromRepo = indicativeCostService.findCommandByID(ID_VALUE);

        assertNotNull(indCostFromRepo, "Indicative Cost Command is Empty");
        verify(indicativeCostRepository, times(1)).findById(anyLong());
        verify(indicativeCostRepository, never()).findAll();
    }

    @Test
    void saveCostCommand() {

        IndicativeCostCommand indicativeCostCommand = new IndicativeCostCommand();
        indicativeCostCommand.setID(ID_VALUE);


        indicativeCostService.saveCostCommand(indicativeCostCommand);

        verify(indicativeCostRepository, times(1)).save(any());
        verify(indicativeCostRepository, never()).findAll();
    }

    @Test
    void deleteById() {

        indicativeCostService.deleteById(ID_VALUE);

        verify(indicativeCostRepository, times(1)).deleteById(anyLong());
        verify(indicativeCostRepository,never()).save(any());


    }
}
