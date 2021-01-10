package com.radello.constructioncompanyorganizer.services;

import com.radello.constructioncompanyorganizer.commands.ConstructionOrderCommand;
import com.radello.constructioncompanyorganizer.converter.ConstructionOrderCommandtoConstructionOrder;
import com.radello.constructioncompanyorganizer.converter.ConstructionOrdertoConstructionOrderCommand;
import com.radello.constructioncompanyorganizer.domain.ConstructionOrder;
import com.radello.constructioncompanyorganizer.domain.Income;
import com.radello.constructioncompanyorganizer.domain.IndicativeCost;
import com.radello.constructioncompanyorganizer.repositories.ConstructionOrderRepository;
import com.radello.constructioncompanyorganizer.services.constructionOrderServices.ConstructionOrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConstructionOrderServiceImplTest {

    private final Long ID_VALUE = 1L;
    private final String ADDRESS_VALUE = "Example Address 3/13";

    Optional<ConstructionOrder> constructionOrderOptional;

    ConstructionOrderServiceImpl constructionOrderService;

    ConstructionOrder constructionOrder;
    @Mock
    ConstructionOrderRepository constructionOrderRepository;

    @Mock
    ConstructionOrdertoConstructionOrderCommand consOrdToConsOrdCommand;

    @Mock
    ConstructionOrderCommandtoConstructionOrder commandToConstructionOrder;

    @BeforeEach
    void setUp() {

        constructionOrderService = new ConstructionOrderServiceImpl
                (constructionOrderRepository, consOrdToConsOrdCommand, commandToConstructionOrder);

        constructionOrder = new ConstructionOrder();
        constructionOrder.setID(ID_VALUE);
        constructionOrder.setAddres(ADDRESS_VALUE);

        Income income = new Income();
        income.setID(ID_VALUE);
        constructionOrder.setIncome(income);

        IndicativeCost indicativeCost1 = new IndicativeCost();
        indicativeCost1.setID(ID_VALUE);
        IndicativeCost indicativeCost2 = new IndicativeCost();
        Long ID_VALUE2 = 2L;
        indicativeCost2.setID(ID_VALUE2);

        constructionOrder.getIndicateCosts().add(indicativeCost1);
        constructionOrder.getIndicateCosts().add(indicativeCost2);

        constructionOrderOptional = Optional.of(constructionOrder);
    }


    @Test
    void findByID() {

        when(constructionOrderRepository.findById(anyLong())).thenReturn(constructionOrderOptional);

        ConstructionOrder constructionOrder1 = constructionOrderService.findByID(1L);

        assertNotNull(constructionOrder1, "Construction Order Not found");
        assertEquals(ID_VALUE, constructionOrderOptional.get().getID());
        verify(constructionOrderRepository, times(1)).findById(anyLong());
        verify(constructionOrderRepository, never()).findAll();
    }

    @Test
    void findByIDNotFound() {

        Optional<ConstructionOrder> constructionOrderOptional1 = Optional.empty();

        when(constructionOrderRepository.findById(anyLong())).thenReturn(constructionOrderOptional1);

        ConstructionOrder constructionOrder1 = constructionOrderService.findByID(anyLong());
        assertNull(constructionOrder1);
        verify(constructionOrderRepository, times(1)).findById(anyLong());
        verify(constructionOrderRepository, never()).findAll();
    }

    @Test
    void findCommandByID() {

        when(constructionOrderRepository.findById(anyLong())).thenReturn(constructionOrderOptional);
        ConstructionOrderCommand constructionOrderCommand = new ConstructionOrderCommand();
        constructionOrderCommand.setID(ID_VALUE);

        when(consOrdToConsOrdCommand.convert(any())).thenReturn(constructionOrderCommand);

        ConstructionOrderCommand consOrdFromRepo = constructionOrderService.findCommandByID(ID_VALUE);

        assertNotNull(consOrdFromRepo, "Construction Order Command is Empty");
        verify(constructionOrderRepository, times(1)).findById(anyLong());
        verify(constructionOrderRepository, never()).findAll();
    }


    @Test
    void saveConstructionOrderCommand() {

        ConstructionOrderCommand constructionOrderCommand = new ConstructionOrderCommand();
        constructionOrderCommand.setID(ID_VALUE);
        constructionOrderCommand.setAddres(ADDRESS_VALUE);

        constructionOrderService.saveConstructionOrderCommand(constructionOrderCommand);

        verify(constructionOrderRepository, times(1)).save(any());
        verify(constructionOrderRepository, never()).findAll();
    }

    @Test
    void deleteById() {

        constructionOrderService.deleteById(ID_VALUE);

        verify(constructionOrderRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getConstructionOrders() {

        constructionOrderService.getConstructionOrders();

        verify(constructionOrderRepository, times(1)).findAll();
        verify(constructionOrderRepository, never()).save(any());
    }
}

